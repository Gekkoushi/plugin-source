package tsuki.site.zeistmanga.pt

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.model.MangaTag
import tsuki.site.zeistmanga.ZeistMangaParser
import tsuki.util.mapToSet
import tsuki.util.parseHtml
import tsuki.util.requireElementById

@Broken("Blocked by Cloudflare challenge")
@MangaSourceParser("GUILDATIERDRAW", "GuildaTierDraw", "pt")
internal class GuildaTierDraw(context: MangaLoaderContext) :
	ZeistMangaParser(context, MangaParserSource.GUILDATIERDRAW, "www.guildatierdraw.top") {

	override suspend fun fetchAvailableTags(): Set<MangaTag> {
		val doc = webClient.httpGet("https://$domain").parseHtml()
		return doc.requireElementById("LinkList2").select("ul li a").mapToSet {
			MangaTag(
				key = it.attr("href").substringBefore("?").substringAfterLast('/'),
				title = it.text(),
				source = source,
			)
		}
	}
}
