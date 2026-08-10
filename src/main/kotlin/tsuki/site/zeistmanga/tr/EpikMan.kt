package tsuki.site.zeistmanga.tr

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.model.MangaTag
import tsuki.site.zeistmanga.ZeistMangaParser
import tsuki.util.mapToSet
import tsuki.util.parseHtml
import tsuki.util.requireElementById

@Broken("Domain has no DNS records — site is gone")
@MangaSourceParser("EPIKMAN", "EpikMan", "tr")
internal class EpikMan(context: MangaLoaderContext) :
	ZeistMangaParser(context, MangaParserSource.EPIKMAN, "www.epikman.ga") {
	override val sateOngoing = "Devam Ediyor"
	override val sateFinished = "Tamamlandı"
	override val mangaCategory = "Seri"

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
