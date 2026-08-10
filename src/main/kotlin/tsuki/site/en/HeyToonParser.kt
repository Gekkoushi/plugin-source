package tsuki.site.en

import okhttp3.Headers
import org.jsoup.nodes.Document
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
import tsuki.util.attrAsRelativeUrl
import tsuki.util.generateUid
import tsuki.util.json.getStringOrNull
import tsuki.util.json.mapJSON
import tsuki.util.mapChapters
import tsuki.util.mapNotNullToSet
import tsuki.util.mapToSet
import tsuki.util.parseHtml
import tsuki.util.parseJsonArray
import tsuki.util.requireSrc
import tsuki.util.selectFirstOrThrow
import tsuki.util.toAbsoluteUrl
import tsuki.util.toRelativeUrl
import tsuki.util.urlEncoded
import java.util.Calendar
import java.util.EnumSet

@MangaSourceParser("HEYTOON", "HeyToon", "en", ContentType.HENTAI)
internal class HeyToonParser(context: MangaLoaderContext) :
	PagedMangaParser(context, MangaParserSource.HEYTOON, pageSize = 54, searchPageSize = 20) {

	override val configKeyDomain = ConfigKey.Domain("heytoon.net")

	override fun onCreateConfig(keys: MutableCollection<ConfigKey<*>>) {
		super.onCreateConfig(keys)
		keys.add(userAgentKey)
	}

	override val availableSortOrders: Set<SortOrder> = EnumSet.of(
		SortOrder.UPDATED,
		SortOrder.POPULARITY,
	)

	override val filterCapabilities: MangaListFilterCapabilities
		get() = MangaListFilterCapabilities(
			isSearchSupported = true,
			isSearchWithFiltersSupported = true,
			isMultipleTagsSupported = true,
		)

	override suspend fun getFilterOptions() = MangaListFilterOptions(
		availableTags = getAvailableGenres(),
	)

	private val headers = Headers.headersOf("X-Requested-With", "XMLHttpRequest")

	override suspend fun getListPage(page: Int, order: SortOrder, filter: MangaListFilter): List<Manga> {
		return when {
			!filter.query.isNullOrEmpty() -> {
				if (page > 1) return emptyList()

				val url = "https://$domain/api/complete-search?keyword=${filter.query.urlEncoded()}"
				val response = webClient.httpGet(url, headers).parseJsonArray()

				response.mapJSON { comic ->
					val linkComic = comic.getString("linkComic")
					val id = linkComic.substringAfterLast("/").substringBefore(".html")
					Manga(
						id = generateUid(id),
						url = linkComic.toRelativeUrl(domain),
						publicUrl = linkComic.toAbsoluteUrl(domain),
						title = comic.getString("title"),
						coverUrl = comic.getStringOrNull("raw_thumb"),
						altTitles = emptySet(),
						rating = RATING_UNKNOWN,
						tags = emptySet(),
						state = null,
						authors = emptySet(),
						source = source,
						contentRating = null,
						largeCoverUrl = null,
						description = null,
						chapters = null,
					)
				}
			}

			else -> {
				val url = buildString {
					append("https://")
					append(domain)
					append("/en/genres")

					filter.tags.firstOrNull()?.let {
						append("/")
						append(it.key)
					}

					append("?orderBy=")
					when (order) {
						SortOrder.POPULARITY -> append("views")
						SortOrder.UPDATED -> append("latest")
						else -> append("latest")
					}

					if (page > 1) {
						append("&page=")
						append(page)
					}
				}

				val doc = webClient.httpGet(url).parseHtml()
				parseMangaList(doc)
			}
		}
	}

	private fun parseMangaList(doc: Document): List<Manga> {
		return doc.select("div.comicItemCon a").map { element ->
			val href = element.attrAsRelativeUrl("href")
			val id = href.substringAfterLast("/").substringBefore(".html").substringBefore("-")
			val img = element.selectFirstOrThrow("img[alt!=badge]")
			Manga(
				id = generateUid(id),
				url = href,
				publicUrl = href.toAbsoluteUrl(domain),
				title = img.attr("title").ifEmpty { img.attr("alt") },
				coverUrl = img.attr("data-src").toAbsoluteUrl(domain),
				altTitles = emptySet(),
				rating = RATING_UNKNOWN,
				tags = emptySet(),
				state = null,
				authors = emptySet(),
				source = source,
				contentRating = null,
				largeCoverUrl = null,
				description = null,
				chapters = null,
			)
		}
	}

	override suspend fun getDetails(manga: Manga): Manga {
		val doc = webClient.httpGet(manga.url.toAbsoluteUrl(domain)).parseHtml()

		val title = doc.selectFirstOrThrow("#titleSubWrapper h1.titCon").text()
		val description = doc.selectFirst("#modal_detail .cont_area p")?.text()
		val genres = parseGenres(doc)
		val status = parseStatus(doc)
		val chapters = parseChapters(doc)

		return manga.copy(
			title = title,
			description = description,
			tags = genres,
			state = status,
			coverUrl = doc.selectFirst("meta[property=og:image]")?.attr("content") ?: manga.coverUrl,
			chapters = chapters,
		)
	}

	private fun parseGenres(doc: Document): Set<MangaTag> {
		return doc.select("div.previewItemCon a[href*=/genres/]").mapNotNullToSet { a ->
			val key = a.attr("href").substringAfterLast("/genres/").substringBefore("?")
			if (key.isEmpty()) return@mapNotNullToSet null
			MangaTag(
				key = key,
				title = key,
				source = source,
			)
		}
	}

	private fun parseStatus(doc: Document): MangaState? {
		return doc.select(".badgeArea span").eachText().let { badges ->
			when {
				badges.any { it.contains("Up") } -> MangaState.ONGOING
				badges.any { it.contains("Completed") } -> MangaState.FINISHED
				else -> null
			}
		}
	}

	private fun parseChapters(doc: Document): List<MangaChapter> {
		return doc.select(".episodeListConPC a#episodeItemCon").mapChapters(reversed = false) { i, element ->
			val href = element.attrAsRelativeUrl("href")
			MangaChapter(
				id = generateUid(href),
				title = element.selectFirstOrThrow(".comicInfo p.episodeStitle").text(),
				number = i + 1f,
				volume = 0,
				url = href,
				scanlator = null,
				uploadDate = parseDateOrNull(element.selectFirst(".comicInfo .episodeDate")?.text()),
				branch = null,
				source = source,
			)
		}
	}

	override suspend fun getPages(chapter: MangaChapter): List<MangaPage> {
		val doc = webClient.httpGet(chapter.url.toAbsoluteUrl(domain)).parseHtml()

		return doc.select("#comicContent img").map { img ->
			val url = img.requireSrc()
			MangaPage(
				id = generateUid(url),
				url = url,
				preview = null,
				source = source,
			)
		}
	}

	private fun getAvailableGenres(): Set<MangaTag> {
		val genres = listOf(
			"Detective",
			"Spin-Off",
			"Mommy",
			"Uncensored",
			"New",
			"In-Law",
			"Cheating",
			"MILF",
			"Harem",
			"College",
			"Business",
			"Supernatural",
			"Thriller",
			"Adventure",
			"Romance",
			"Drama",
		)

		return genres.mapToSet { genre ->
			MangaTag(
				key = genre,
				title = genre,
				source = source,
			)
		}
	}

	private fun parseDateOrNull(dateStr: String?): Long {
		if (dateStr.isNullOrEmpty()) return 0
		return try {
			val parts = dateStr.split(" ")
			if (parts.size != 3) return 0

			val month = when (parts[0].lowercase()) {
				"jan" -> Calendar.JANUARY
				"feb" -> Calendar.FEBRUARY
				"mar" -> Calendar.MARCH
				"apr" -> Calendar.APRIL
				"may" -> Calendar.MAY
				"jun" -> Calendar.JUNE
				"jul" -> Calendar.JULY
				"aug" -> Calendar.AUGUST
				"sep" -> Calendar.SEPTEMBER
				"oct" -> Calendar.OCTOBER
				"nov" -> Calendar.NOVEMBER
				"dec" -> Calendar.DECEMBER
				else -> return 0
			}

			val day = parts[1].removeSuffix(",").toIntOrNull() ?: return 0
			val year = parts[2].toIntOrNull() ?: return 0

			Calendar.getInstance().apply {
				set(Calendar.YEAR, year)
				set(Calendar.MONTH, month)
				set(Calendar.DAY_OF_MONTH, day)
				set(Calendar.HOUR_OF_DAY, 0)
				set(Calendar.MINUTE, 0)
				set(Calendar.SECOND, 0)
				set(Calendar.MILLISECOND, 0)
			}.timeInMillis
		} catch (e: Exception) {
			0
		}
	}
}
