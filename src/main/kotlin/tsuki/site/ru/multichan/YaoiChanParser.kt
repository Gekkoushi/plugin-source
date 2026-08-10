package tsuki.site.ru.multichan

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.config.ConfigKey
import tsuki.model.Manga
import tsuki.model.MangaChapter
import tsuki.model.MangaParserSource
import tsuki.model.SortOrder
import tsuki.util.*
import java.util.EnumSet

@MangaSourceParser("YAOICHAN", "Яой-тян", "ru")
internal class YaoiChanParser(context: MangaLoaderContext) : ChanParser(context, MangaParserSource.YAOICHAN) {

	override val configKeyDomain = ConfigKey.Domain(
		"v9.yaoi-chan.me",
		"v10.yaoi-chan.me",
		"v3.yaoi-chan.me",
		"v2.yaoi-chan.me",
		"v1.yaoi-chan.me",
		"yaoi-chan.me",
	)

	override val availableSortOrders: Set<SortOrder> = EnumSet.of(SortOrder.NEWEST)

	override suspend fun getDetails(manga: Manga): Manga {
		val doc = webClient.httpGet(manga.url.toAbsoluteUrl(domain)).parseHtml()
		val root = doc.body().requireElementById("dle-content")
		return manga.copy(
			description = root.getElementById("description")?.html()?.substringBeforeLast("<div"),
			largeCoverUrl = root.getElementById("cover")?.absUrl("src"),
			chapters = root.select("table.table_cha").flatMap { table ->
				table.select("div.manga")
			}.mapNotNull { it.selectFirst("a") }.mapChapters(reversed = true) { i, a ->
				val href = a.attrAsRelativeUrl("href")
				MangaChapter(
					id = generateUid(href),
					title = a.text(),
					number = i + 1f,
					volume = 0,
					url = href,
					uploadDate = 0L,
					source = source,
					scanlator = null,
					branch = null,
				)
			},
		)
	}
}
