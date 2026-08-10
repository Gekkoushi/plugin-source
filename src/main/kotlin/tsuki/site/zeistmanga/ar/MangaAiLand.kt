package tsuki.site.zeistmanga.ar

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.model.MangaTag
import tsuki.site.zeistmanga.ZeistMangaParser
import tsuki.util.mapToSet
import tsuki.util.parseHtml
import tsuki.util.requireElementById

@MangaSourceParser("MANGAAILAND", "MangaAiLand", "ar")
internal class MangaAiLand(context: MangaLoaderContext) :
	ZeistMangaParser(context, MangaParserSource.MANGAAILAND, "manga-ai-land.blogspot.com") {
	override val sateOngoing: String = "مستمر"
	override val sateFinished: String = "مكتملة"
	override val sateAbandoned: String = "متوقفة"

	override suspend fun fetchAvailableTags(): Set<MangaTag> {
		val doc = webClient.httpGet("https://$domain").parseHtml()
		return doc.requireElementById("LinkList1").select("ul li a").mapToSet {
			MangaTag(
				key = it.attr("href").substringBefore("?").substringAfterLast('/'),
				title = it.text(),
				source = source,
			)
		}
	}
}
