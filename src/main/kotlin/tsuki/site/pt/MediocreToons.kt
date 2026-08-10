package tsuki.site.pt

import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import org.json.JSONObject
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.config.ConfigKey
import tsuki.core.PagedMangaParser
import tsuki.model.ContentType
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
import tsuki.util.generateUid
import tsuki.util.json.mapJSON
import tsuki.util.json.mapJSONNotNull
import tsuki.util.oneOrThrowIfMany
import tsuki.util.parseJson
import tsuki.util.parseSafe
import tsuki.util.toTitleCase
import java.text.SimpleDateFormat
import java.util.EnumSet

@MangaSourceParser("MEDIOCRETOONS", "MediocreToons", "pt")
internal class MediocreToons(context: MangaLoaderContext) : PagedMangaParser(
	context,
	source = MangaParserSource.MEDIOCRETOONS,
	pageSize = 20,
) {
	override val configKeyDomain = ConfigKey.Domain("mediocretoons.com")
	private val apiUrl = "https://api.mediocretoons.site"
	private val cdnUrl = "https://cdn.mediocretoons.site"

	override fun getRequestHeaders() = super.getRequestHeaders().newBuilder()
		.add(CommonHeaders.REFERER, "https://$domain/")
		.add(CommonHeaders.ORIGIN, "https://$domain")
		.add(CommonHeaders.X_APP_KEY, "toons-mediocre-app")
		.build()

	override val availableSortOrders: Set<SortOrder> = EnumSet.of(
		SortOrder.UPDATED,
	)

	override val filterCapabilities: MangaListFilterCapabilities
		get() = MangaListFilterCapabilities(
			isSearchSupported = true,
			isSearchWithFiltersSupported = true,
			isMultipleTagsSupported = true,
		)

	override suspend fun getFilterOptions(): MangaListFilterOptions {
		return MangaListFilterOptions(
			availableTags = fetchAvailableTags(),
			availableStates = EnumSet.of(
				MangaState.ONGOING,
				MangaState.FINISHED,
				MangaState.ABANDONED,
			),
			availableContentTypes = EnumSet.of(
				ContentType.MANGA,
				ContentType.HENTAI,
			),
		)
	}

	private val chapterDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", sourceLocale)

	override suspend fun getListPage(page: Int, order: SortOrder, filter: MangaListFilter): List<Manga> {
		val url = when {
			!filter.query.isNullOrEmpty() || filter.tags.isNotEmpty() || filter.states.isNotEmpty() || filter.types.isNotEmpty() -> buildSearchUrl(
				page,
				filter,
			)

			else -> {
				"$apiUrl/obras/recentes".toHttpUrl().newBuilder()
					.addQueryParameter("limite", pageSize.toString())
					.addQueryParameter("pagina", page.toString())
					.addQueryParameter("formato", "5")
					.build()
			}
		}

		val response = webClient.httpGet(url).parseJson()
		val results = response.optJSONArray("data") ?: return emptyList()
		return results.mapJSON { parseMangaFromJson(it) }
	}

	private fun buildSearchUrl(page: Int, filter: MangaListFilter): HttpUrl {
		val builder = "$apiUrl/obras".toHttpUrl().newBuilder().addQueryParameter("limite", pageSize.toString())
			.addQueryParameter("pagina", page.toString())

		// Add search query
		if (!filter.query.isNullOrEmpty()) {
			builder.addQueryParameter("string", filter.query)
		}

		// Add tags
		filter.tags.forEach { tag ->
			builder.addQueryParameter("tags[]", tag.key)
		}

		if (filter.types.isNotEmpty()) {
			filter.types.forEach { contentType ->
				val formatId = when (contentType) {
					ContentType.MANGA -> "5"
					ContentType.HENTAI -> "8"
					else -> null
				}
				formatId?.let { builder.addQueryParameter("formato[]", it) }
			}
		}

		// Add status
		filter.states.oneOrThrowIfMany()?.let { state ->
			val statusId = when (state) {
				MangaState.ONGOING -> "2"
				MangaState.FINISHED -> "4"
				MangaState.PAUSED -> "3"
				else -> null
			}
			statusId?.let { builder.addQueryParameter("status", it) }
		}

		return builder.build()
	}

	private fun parseMangaFromJson(json: JSONObject): Manga {
		val id = json.getInt("id")
		val name = json.getString("nome")
		val slug = name.lowercase().replace(Regex("[^a-z0-9]+"), "-").trim('-')
		val coverPath = json.optString("imagem", "")

		val coverUrl = when {
			coverPath.startsWith("http") -> coverPath
			coverPath.isNotEmpty() -> "$cdnUrl/obras/$id/$coverPath"
			else -> ""
		}

		val rating = RATING_UNKNOWN

		return Manga(
			id = generateUid(id.toLong()),
			title = name,
			url = "/obra/$id/$slug",
			publicUrl = "https://$domain/obra/$id/$slug",
			coverUrl = coverUrl,
			source = source,
			rating = rating,
			altTitles = emptySet(),
			contentRating = null,
			tags = emptySet(),
			state = null,
			authors = emptySet(),
			largeCoverUrl = null,
			description = null,
			chapters = null,
		)
	}

	override suspend fun getDetails(manga: Manga): Manga {
		val mangaId = manga.url.substringAfter("/obra/").substringBefore("/")
		val response = webClient.httpGet("$apiUrl/obras/$mangaId").parseJson()

		val description = response.optString("descricao").replace(Regex("</?[^>]+>"), "").replace("\\/", "/")
			.replace(Regex("\\s+"), " ").trim()

		val status = response.optJSONObject("status")?.let { statusObj ->
			when (statusObj.optString("nome").lowercase()) {
				"ativo", "em andamento" -> MangaState.ONGOING
				"concluído", "completo" -> MangaState.FINISHED
				"hiato" -> MangaState.PAUSED
				"cancelado" -> MangaState.ABANDONED
				else -> null
			}
		}

		val tags = response.optJSONArray("tags")?.mapJSON { tagJson ->
			MangaTag(
				key = tagJson.getInt("id").toString(),
				title = tagJson.getString("nome").toTitleCase(),
				source = source,
			)
		}?.toSet() ?: emptySet()

		val chapters = response.optJSONArray("capitulos")?.mapJSON { chapterJson ->
			parseChapter(chapterJson)
		}?.sortedBy { it.number } ?: emptyList()

		return manga.copy(
			title = response.optString("nome", manga.title),
			description = description,
			state = status,
			tags = tags,
			chapters = chapters,
		)
	}

	private fun parseChapter(json: JSONObject): MangaChapter {
		val chapterId = json.getInt("id")
		val chapterName = json.getString("nome")
		val chapterDate = json.optString("criado_em")

		val chapterNumber = json.optString("numero").toFloat()

		return MangaChapter(
			id = generateUid(chapterId.toLong()),
			title = "Capítulo $chapterName",
			number = chapterNumber,
			url = "/capitulo/$chapterId",
			uploadDate = chapterDateFormat.parseSafe(chapterDate),
			source = source,
			volume = 0,
			scanlator = null,
			branch = null,
		)
	}


	override suspend fun getPages(chapter: MangaChapter): List<MangaPage> {
		val chapterId = chapter.url.substringAfter("/capitulo/")

		val response = webClient.httpGet("$apiUrl/capitulos/$chapterId").parseJson()

		val pagesArray = response.optJSONArray("paginas") ?: throw Exception("No pages found in chapter")

		val obraInfo = response.optJSONObject("obra") ?: throw Exception("Obra information not found")
		val obraId = obraInfo.getInt("id")
		val chapterNumber = response.optString("numero", "")

		return pagesArray.mapJSONNotNull { pageJson ->
			val pageSrc = pageJson.optString("src")

			if (pageSrc.isEmpty()) return@mapJSONNotNull null

			val imageUrl = when {
				pageSrc.startsWith("http") -> pageSrc
				else -> "$cdnUrl/obras/$obraId/capitulos/$chapterNumber/$pageSrc"
			}

			MangaPage(
				id = generateUid(imageUrl),
				url = imageUrl,
				source = source,
				preview = null,
			)
		}
	}

	private suspend fun fetchAvailableTags(): Set<MangaTag> {
		val url = "$apiUrl/tags"
		val body = webClient.httpGet(url).body.string().trim()

		val tagsArray = if (body.startsWith("[")) {
			org.json.JSONArray(body)
		} else {
			val json = JSONObject(body)
			json.optJSONArray("tags") ?: json.optJSONArray("data") ?: org.json.JSONArray()
		}

		return tagsArray.mapJSONNotNull { tagJson ->
			MangaTag(
				key = tagJson.getInt("id").toString(),
				title = tagJson.getString("nome").toTitleCase(),
				source = source,
			)
		}.toSet()
	}
}
