package tsuki.site.pt

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.config.ConfigKey
import tsuki.core.PagedMangaParser
import tsuki.exception.ParseException
import tsuki.model.Manga
import tsuki.model.MangaChapter
import tsuki.model.MangaListFilter
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaListFilterOptions
import tsuki.model.MangaPage
import tsuki.model.MangaParserSource
import tsuki.model.MangaState
import tsuki.model.MangaTag
import tsuki.model.RATING_UNKNOWN
import tsuki.model.SortOrder
import tsuki.network.CommonHeaders
import tsuki.util.await
import tsuki.util.generateUid
import tsuki.util.json.extractNextJsTyped
import tsuki.util.json.mapJSON
import tsuki.util.parseHtml
import tsuki.util.parseJson
import tsuki.util.toAbsoluteUrl
import tsuki.util.toTitleCase
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.EnumSet
import java.util.Locale
import java.util.TimeZone

@Broken("Refactor code")
@MangaSourceParser("TAIYO", "Taiyō", "pt")
internal class TaiyoParser(context: MangaLoaderContext) :
	PagedMangaParser(context, MangaParserSource.TAIYO, 20) {

	override val configKeyDomain = ConfigKey.Domain("taiyo.moe")

	private val cdnDomain = "cdn.$domain"
	private val meiliDomain = "meilisearch.$domain"

	override fun onCreateConfig(keys: MutableCollection<ConfigKey<*>>) {
		super.onCreateConfig(keys)
		keys.add(userAgentKey)
	}

	override val availableSortOrders: Set<SortOrder> = EnumSet.of(
		SortOrder.UPDATED,
		SortOrder.POPULARITY
	)

	override val filterCapabilities: MangaListFilterCapabilities
		get() = MangaListFilterCapabilities(
			isSearchSupported = true,
			isMultipleTagsSupported = true,
			isSearchWithFiltersSupported = true,
		)

	override suspend fun getFilterOptions(): MangaListFilterOptions {
		val queryObj = JSONObject().apply {
			put("indexUid", "medias")
			put("q", "")
			put("facets", JSONArray().put("genres"))
			put("limit", 0)
			put("filter", JSONArray().put("deletedAt IS NULL"))
		}
		val body = JSONObject().apply {
			put("queries", JSONArray().put(queryObj))
		}
		val response = meiliPost(body)
		val facets = response.getJSONArray("results")
			.getJSONObject(0)
			.optJSONObject("facetDistribution")
			?.optJSONObject("genres")

		val tags = mutableSetOf<MangaTag>()
		if (facets != null) {
			for (key in facets.keys()) {
				tags.add(
					MangaTag(
						key = key,
						title = key.lowercase().replace('_', ' ').toTitleCase(sourceLocale),
						source = source,
					),
				)
			}
		}
		return MangaListFilterOptions(availableTags = tags)
	}

	/**
	 * Make a raw OkHttp POST to MeiliSearch, bypassing webClient interceptors.
	 */
	private suspend fun meiliPost(body: JSONObject): JSONObject {
		val mediaType = "application/json; charset=utf-8".toMediaType()
		val requestBody = body.toString().toRequestBody(mediaType)
		val request = Request.Builder()
			.url("https://$meiliDomain/multi-search")
			.post(requestBody)
			.addHeader(CommonHeaders.AUTHORIZATION, "Bearer $MEILIKEY")
			.addHeader(CommonHeaders.ORIGIN, "https://$domain")
			.addHeader(CommonHeaders.REFERER, "https://$domain/")
			.addHeader(CommonHeaders.ACCEPT, "application/json")
			.tag(source)
			.build()
		val client = context.httpClient.newBuilder()
			.apply { interceptors().clear() }
			.build()
		return client.newCall(request).await().parseJson()
	}

	override suspend fun getListPage(page: Int, order: SortOrder, filter: MangaListFilter): List<Manga> {
		val query = filter.query.orEmpty()
		val offset = (page - 1) * pageSize
		val sort = when (order) {
			SortOrder.UPDATED -> "updatedAt:desc"
			SortOrder.POPULARITY -> "updatedAt:desc"
			else -> "updatedAt:desc"
		}

		val filters = JSONArray().put("deletedAt IS NULL")
		for (tag in filter.tags) {
			filters.put("genres = '${tag.key}'")
		}

		val queryObj = JSONObject().apply {
			put("indexUid", "medias")
			put("q", query)
			put("filter", filters)
			put("limit", pageSize)
			put("offset", offset)
			put("sort", JSONArray().put(sort))
		}
		val body = JSONObject().apply {
			put("queries", JSONArray().put(queryObj))
		}

		val response = meiliPost(body)

		val results = response.getJSONArray("results")
		if (results.length() == 0) return emptyList()

		val hits = results.getJSONObject(0).getJSONArray("hits")
		return hits.mapJSON { hit ->
			val mediaId = hit.getString("id")
			val coverId = hit.optString("mainCoverId", "")
			val url = "/media/$mediaId"

			val title = hit.optJSONArray("titles")
				?.mapJSON { obj ->
					obj.optString("language") to
						(obj.optString("title") to obj.optBoolean("isMainTitle", false))
				}?.let { list ->
					val ptBr = list.firstOrNull { it.first == "pt_br" }?.second?.first
					val main = list.firstOrNull { it.second.second }?.second?.first.orEmpty()
					ptBr ?: main
				}.orEmpty()

			Manga(
				id = generateUid(url),
				url = url,
				publicUrl = "https://$domain$url",
				title = title,
				coverUrl = "https://$cdnDomain/medias/$mediaId/covers/$coverId.jpg",
				altTitles = emptySet(),
				rating = RATING_UNKNOWN,
				tags = emptySet(),
				description = null,
				state = null,
				authors = emptySet(),
				contentRating = null,
				source = source,
			)
		}
	}

	override suspend fun getDetails(manga: Manga): Manga {
		val mediaId = manga.url.substringAfter("/media/")

		val body = JSONObject().apply {
			put("queries", JSONArray().put(
				JSONObject().apply {
					put("indexUid", "medias")
					put("q", "")
					put("filter", JSONArray()
						.put("id = '$mediaId'")
						.put("deletedAt IS NULL")
					)
					put("limit", 1)
				}
			))
		}

		val hits = meiliPost(body).getJSONArray("results")
			.getJSONObject(0)
			.optJSONArray("hits")
			?: JSONArray()

		val hit = hits.optJSONObject(0)
		val status = hit?.optString("status", "")
		val synopsis = hit?.optString("synopsis", "")
			?.ifEmpty { null }

		val coverId = hit?.optString("mainCoverId", "")
			?.ifEmpty { null }

		val titles = hit?.optJSONArray("titles")
			?.mapJSON { it }
			.orEmpty()

		val mainTitle = titles
			.firstOrNull { it.optString("language") == "pt_br" }
			?.optString("title")
			?: titles.firstOrNull { it.optBoolean("isMainTitle", false) }
				?.optString("title")
			?: manga.title

		val genres = hit?.optJSONArray("genres")?.let { array ->
			(0 until array.length()).map { array.getString(it) }
				.mapTo(mutableSetOf()) { genre ->
					MangaTag(
						key = genre.lowercase(),
						title = genre.lowercase()
							.replace('_', ' ')
							.toTitleCase(sourceLocale),
						source = source,
					)
				}
			} ?: emptySet()

		// Fetch chapters via tRPC API
		val chapters = fetchChaptersViaTrpc(mediaId)

		val coverUrl = if (!coverId.isNullOrEmpty()) {
			"https://$cdnDomain/medias/$mediaId/covers/$coverId.jpg"
		} else {
			manga.coverUrl
		}

		val mangaState = when (status?.uppercase()) {
			"ONGOING", "RELEASING" -> MangaState.ONGOING
			"FINISHED", "COMPLETED" -> MangaState.FINISHED
			"HIATUS" -> MangaState.PAUSED
			"CANCELLED", "DROPPED" -> MangaState.ABANDONED
			else -> null
		}

		return manga.copy(
			title = mainTitle,
			coverUrl = coverUrl,
			description = synopsis,
			tags = genres,
			state = mangaState,
			chapters = chapters,
		)
	}

	/**
	 * Fetch all chapters for a media via the tRPC API.
	 * Endpoint: /api/trpc/chapters.getByMediaId?input={"json":{"mediaId":"...","page":N,"perPage":100}}
	 * Response: {"result":{"data":{"json":{"chapters":[...],"totalPages":N}}}}
	 */
	private suspend fun fetchChaptersViaTrpc(mediaId: String): List<MangaChapter> {
		val chapters = mutableListOf<MangaChapter>()
		val perPage = 100
		var page = 1
		var totalPages: Int

		val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
		dateFormat.timeZone = TimeZone.getTimeZone("UTC")

		do {
			val input = "{\"json\":{\"mediaId\":\"$mediaId\",\"page\":$page,\"perPage\":$perPage}}"
			val encodedInput = withContext(Dispatchers.IO) {
				URLEncoder.encode(input, "UTF-8")
			}
			val apiUrl = "https://$domain/api/trpc/chapters.getByMediaId?input=$encodedInput"

			val response = webClient.httpGet(apiUrl).parseJson()
			val data = response.getJSONObject("result")
				.getJSONObject("data")
				.getJSONObject("json")

			totalPages = data.optInt("totalPages", 1)
			val chaptersArray = data.getJSONArray("chapters")

			for (i in 0 until chaptersArray.length()) {
				val chapterObj = chaptersArray.getJSONObject(i)
				val chId = chapterObj.getString("id")
				val number = chapterObj.optDouble("number", 0.0)
				val volume = chapterObj.optInt("volume", 0)
				val title = chapterObj.optString("title", "").let {
					if (it == "null" || it.isEmpty()) null else it
				}
				val chapterName = title ?: "Capítulo ${number.toInt()}"
				val url = "/chapter/$chId/1"

				// Parse upload date
				val uploadDate = try {
					val dateStr = chapterObj.optString("createdAt", "")
					if (dateStr.isNotEmpty()) {
						dateFormat.parse(dateStr.substringBefore("."))?.time ?: 0L
					} else {
						0L
					}
				} catch (_: Exception) {
					0L
				}

				// Get scanlator name
				val scanlator = try {
					val scans = chapterObj.optJSONArray("scans")
					if (scans != null && scans.length() > 0) {
						scans.getJSONObject(0).optString("name", null)
					} else {
						null
					}
				} catch (_: Exception) {
					null
				}

				chapters.add(
					MangaChapter(
						id = generateUid(url),
						title = chapterName,
						number = number.toFloat(),
						volume = volume,
						url = url,
						scanlator = scanlator,
						uploadDate = uploadDate,
						branch = null,
						source = source,
					),
				)
			}

			page++
		} while (page <= totalPages)

		return chapters.sortedBy { it.number }
	}

	override suspend fun getPages(chapter: MangaChapter): List<MangaPage> {
		val fullUrl = chapter.url.toAbsoluteUrl(domain)
		val doc = webClient.httpGet(fullUrl).parseHtml()
		val chapterObj = doc.extractNextJsTyped<JSONObject> { json ->
			json is JSONObject && json.has("pages") && json.has("media")
		} ?: throw ParseException("Could not find page data", chapter.url)

		val chapterId = chapter.url.substringAfter("/chapter/").substringBefore("/")
		val mediaId = chapterObj.getJSONObject("media").getString("id")
		val pagesArray = chapterObj.getJSONArray("pages")
		val pages = mutableListOf<MangaPage>()

		for (i in 0 until pagesArray.length()) {
			val pageObj = pagesArray.getJSONObject(i)
			val pageId = pageObj.getString("id")
			val ext = pageObj.optString("extension", "jpg")
			val imageUrl = "https://$cdnDomain/medias/$mediaId/chapters/$chapterId/$pageId.$ext"

			pages.add(
				MangaPage(
					id = generateUid(imageUrl),
					url = imageUrl,
					preview = null,
					source = source,
				),
			)
		}

		return pages
	}

	companion object {
		private const val MEILIKEY = "48aa86f73de09a7705a2938a1a35e5a12cff6519695fcad395161315182286e5"
	}
}
