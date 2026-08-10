package tsuki.site.vi

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.config.ConfigKey
import tsuki.core.PagedMangaParser
import tsuki.model.ContentRating
import tsuki.model.Favicon
import tsuki.model.Favicons
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
import tsuki.network.UserAgents
import tsuki.util.generateUid
import tsuki.util.json.mapJSON
import tsuki.util.json.mapJSONToSet
import tsuki.util.oneOrThrowIfMany
import tsuki.util.parseJson
import tsuki.util.parseSafe
import tsuki.util.urlEncoded
import java.text.SimpleDateFormat
import java.util.EnumSet
import java.util.Locale
import java.util.TimeZone

@MangaSourceParser("OTRUYEN", "Ổ Truyện", "vi")
internal class OTruyenParser(context: MangaLoaderContext) :
	PagedMangaParser(context, MangaParserSource.OTRUYEN, 24) {

	override val configKeyDomain = ConfigKey.Domain("otruyenapi.com")

	override val userAgentKey = ConfigKey.UserAgent(UserAgents.KOTATSU)

	override fun onCreateConfig(keys: MutableCollection<ConfigKey<*>>) {
		super.onCreateConfig(keys)
		keys.remove(configKeyDomain)
		keys.remove(userAgentKey)
	}

	override suspend fun getFavicons(): Favicons {
		return Favicons(
			listOf(
				Favicon(
					"https://otruyen.cc/favicon.ico", 32, null),
			),
			domain,
		)
	}

	override val availableSortOrders: Set<SortOrder> = EnumSet.of(SortOrder.NEWEST)

	override val filterCapabilities: MangaListFilterCapabilities
		get() = MangaListFilterCapabilities(
			isSearchSupported = true,
		)

	override suspend fun getFilterOptions(): MangaListFilterOptions {
		return MangaListFilterOptions(
			availableTags = fetchTags(),
			availableStates = EnumSet.of(
				MangaState.ONGOING,
				MangaState.FINISHED,
				MangaState.UPCOMING,
			),
		)
	}

    override suspend fun getListPage(page: Int, order: SortOrder, filter: MangaListFilter): List<Manga> {
        val url = buildString {
            append("https://")
            append(domain)
            when {
                !filter.query.isNullOrEmpty() -> {
                    append("/v1/api/tim-kiem?keyword=")
                    append(filter.query.urlEncoded())
                    append("&page=")
                    append(page.toString())
                }

                else -> {
                    if (!filter.tags.isEmpty()) {
                        filter.tags.oneOrThrowIfMany()?.let {
                            append("/v1/api/the-loai/")
                            append(it.key)
                            append("?page=")
                            append(page)
                        }
                    } else if (filter.states.isNotEmpty()) {
                        filter.states.oneOrThrowIfMany()?.let {
                            append(
                                when (it) {
                                    MangaState.FINISHED -> "/v1/api/danh-sach/hoan-thanh?page=${page}"
                                    MangaState.UPCOMING -> "/v1/api/danh-sach/sap-ra-mat?page=${page}"
                                    else -> "/v1/api/danh-sach/dang-phat-hanh?page=${page}" // ONGOING
                                }
                            )
                        }
                    } else {
                        append("/v1/api/danh-sach/truyen-moi") // SortOrder.NEWEST
                        append("?page=${page}")
                    }
                }
            }
        }

        val json = try {
            webClient.httpGet(url).parseJson()
        } catch (_: Exception) {
            return emptyList()
        }

        val items = json.getJSONObject("data").getJSONArray("items")
        return items.mapJSON { jo ->
            val slug = jo.getString("slug")
            Manga(
                id = generateUid(slug.hashCode().toLong()),
                url = slug,
                publicUrl = "https://otruyen.cc/truyen-tranh/${slug}",
                title = jo.getString("name"),
                altTitles = emptySet(),
                coverUrl = "https://img.otruyenapi.com/uploads/comics/${jo.getString("thumb_url")}",
                authors = emptySet(),
                tags = emptySet(),
                state = when (jo.getString("status")) {
                    "ongoing" -> MangaState.ONGOING
                    "coming_soon" -> MangaState.UPCOMING
                    "completed" -> MangaState.FINISHED
                    else -> null
                },
                contentRating = if (isNsfwSource) ContentRating.ADULT else null,
                source = source,
                rating = RATING_UNKNOWN,
            )
        }
    }

	override suspend fun getDetails(manga: Manga): Manga {
		val url = "https://$domain/v1/api/truyen-tranh/${manga.url}"
		val json = webClient.httpGet(url).parseJson()
		val data = json.getJSONObject("data")
		val item = data.getJSONObject("item")

		val chapters = item.getJSONArray("chapters")
			.mapJSON { it.getJSONArray("server_data") }
			.flatMap { array -> (0 until array.length()).map { array.getJSONObject(it) } }
			.map { jo ->
				val apiData = jo.getString("chapter_api_data")
				MangaChapter(
					id = generateUid(apiData),
					title = jo.optString("chapter_title").ifBlank {
						"Chương ${jo.optString("chapter_name")}"
					},
					number = jo.optString("chapter_name").toFloatOrNull() ?: 0f,
					volume = 0,
					url = apiData,
					scanlator = null,
					uploadDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ROOT).apply {
						timeZone = TimeZone.getTimeZone("UTC+7")
					}.parseSafe(item.optString("updatedAt")),
					branch = null,
					source = source
				)
			}

		return manga.copy(
			altTitles = item.getJSONArray("origin_name").let { jsonArray ->
				(0 until jsonArray.length()).mapTo(mutableSetOf()) { jsonArray.getString(it) }
			},
			authors = item.getJSONArray("author").let { jsonArray ->
				(0 until jsonArray.length()).mapTo(mutableSetOf()) { jsonArray.getString(it) }
			},
			tags = item.optJSONArray("category").mapJSONToSet { jo ->
				MangaTag(
					title = jo.optString("name"),
					key = jo.optString("slug"),
					source = source
				)
			},
			description = item.optString("content"),
			state = when (item.optString("status")) {
				"ongoing" -> MangaState.ONGOING
				"coming_soon" -> MangaState.UPCOMING
				"completed" -> MangaState.FINISHED
				else -> null
			},
			chapters = chapters,
		)
	}

	override suspend fun getPages(chapter: MangaChapter): List<MangaPage> {
		val json = webClient.httpGet(chapter.url).parseJson()
		val data = json.getJSONObject("data")
		val item = data.getJSONObject("item")
		val domainCdn = data.optString("domain_cdn")
		val chapterPath = item.optString("chapter_path")

		return item.getJSONArray("chapter_image").mapJSON { page ->
			val imageFile = page.optString("image_file")
			val imgUrl = "$domainCdn/$chapterPath/$imageFile"
			MangaPage(
				id = generateUid(imgUrl),
				url = imgUrl,
				preview = null,
				source = source
			)
		}
	}

	private suspend fun fetchTags(): Set<MangaTag> {
		val url = "https://$domain/v1/api/the-loai"
		val items = webClient.httpGet(url)
			.parseJson()
			.getJSONObject("data")
			.getJSONArray("items")

		return items.mapJSONToSet { jo ->
			MangaTag(
				title = jo.getString("name"),
				key = jo.getString("slug"),
				source = source,
			)
		}
	}
}
