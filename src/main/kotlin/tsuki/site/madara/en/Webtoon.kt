package tsuki.site.madara.en

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@Broken("Domain has no DNS records — site is gone")
@MangaSourceParser("WEBTOON", "Webtoon.uk", "en")
internal class Webtoon(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.WEBTOON, "webtoon.uk", 20) {
	override val tagPrefix = "manhwa-genre/"
	override val postReq = true
}
