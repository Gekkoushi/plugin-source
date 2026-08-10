package tsuki.site.zeistmanga.id

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.model.MangaTag
import tsuki.site.zeistmanga.ZeistMangaParser
import tsuki.util.*

@Broken("Domain has no DNS records — site is gone")
@MangaSourceParser("MIKOROKU", "Mikoroku", "id", ContentType.HENTAI)
internal class Mikoroku(context: MangaLoaderContext) :
	ZeistMangaParser(context, MangaParserSource.MIKOROKU, "www.mikoroku.web.id") {

	override suspend fun fetchAvailableTags(): Set<MangaTag> {
		val doc = webClient.httpGet("https://$domain").parseHtml()
		return doc.requireElementById("Genre").select("div.items-center").mapToSet {
			MangaTag(
				key = it.selectFirstOrThrow("input").attr("value"),
				title = it.selectFirstOrThrow("label").text().substringBefore('('),
				source = source,
			)
		}
	}
}
