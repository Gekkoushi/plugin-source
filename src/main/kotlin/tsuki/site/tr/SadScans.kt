package tsuki.site.tr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.config.ConfigKey
import tsuki.core.SinglePageMangaParser
import tsuki.model.*
import tsuki.util.*
import java.text.SimpleDateFormat
import java.util.*

@MangaSourceParser("SADSCANS", "SadScans", "tr")
internal class SadScans(context: MangaLoaderContext) :
	SinglePageMangaParser(context, MangaParserSource.SADSCANS) {

	override val configKeyDomain = ConfigKey.Domain("sadscans.com")

	override fun onCreateConfig(keys: MutableCollection<ConfigKey<*>>) {
		super.onCreateConfig(keys)
		keys.add(userAgentKey)
	}

	override val availableSortOrders: Set<SortOrder> = EnumSet.of(SortOrder.ALPHABETICAL)

	override val filterCapabilities: MangaListFilterCapabilities
		get() = MangaListFilterCapabilities(
			isSearchSupported = true,
			isSearchWithFiltersSupported = true,
		)

	override suspend fun getFilterOptions() = MangaListFilterOptions()

	override suspend fun getList(order: SortOrder, filter: MangaListFilter): List<Manga> {
		val url = buildString {
			append("https://")
			append(domain)
			append("/series")
			filter.query?.let {
				append("?search=")
				append(filter.query.urlEncoded())
			}
		}

		val doc = webClient.httpGet(url).parseHtml()
		return doc.select(".series-list").map { div ->
			val href = "/" + div.selectFirstOrThrow("a").attrAsRelativeUrl("href")
			Manga(
				id = generateUid(href),
				title = div.selectFirstOrThrow("h2").text(),
				altTitles = emptySet(),
				url = href,
				publicUrl = href.toAbsoluteUrl(domain),
				rating = RATING_UNKNOWN,
				contentRating = null,
				coverUrl = div.selectFirstOrThrow("img").src()?.toAbsoluteUrl(domain).orEmpty(),
				tags = emptySet(),
				state = null,
				authors = emptySet(),
				source = source,
			)
		}
	}

	override suspend fun getDetails(manga: Manga): Manga {
		val doc = webClient.httpGet(manga.url.toAbsoluteUrl(domain)).parseHtml()
		val dateFormat = SimpleDateFormat("dd MMM, yy", Locale.ENGLISH)
		val author = doc.selectLast(".author span")?.textOrNull()
		return manga.copy(
			state = when (doc.select(".status span").last()?.text()) {
				"Devam ediyor" -> MangaState.ONGOING
				"Tamamlandı" -> MangaState.FINISHED
				else -> null
			},
			tags = emptySet(),
			authors = setOfNotNull(author),
			description = doc.selectFirstOrThrow(".summary").html(),
			chapters = doc.select(".chap-section .chap")
				.mapChapters(reversed = true) { i, div ->
					val a = div.selectFirstOrThrow("a")
					val url = "/" + a.attrAsRelativeUrl("href")
					MangaChapter(
						id = generateUid(url),
						title = a.textOrNull(),
						number = i + 1f,
						volume = 0,
						url = url,
						scanlator = null,
						uploadDate = dateFormat.parseSafe(div.select(".detail span").last()?.text()),
						branch = null,
						source = source,
					)
				},
		)
	}

	override suspend fun getPages(chapter: MangaChapter): List<MangaPage> {
		val fullUrl = chapter.url.toAbsoluteUrl(domain)
		val doc = webClient.httpGet(fullUrl).parseHtml()
		return doc.select(".swiper-slide img").map { img ->
			val url = img.requireSrc().toRelativeUrl(domain)
			MangaPage(
				id = generateUid(url),
				url = url,
				preview = null,
				source = source,
			)
		}
	}
}
