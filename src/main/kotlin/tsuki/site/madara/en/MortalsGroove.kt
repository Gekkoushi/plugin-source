package tsuki.site.madara.en

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@Broken("Site is gone — root redirects to an unrelated domain")
@MangaSourceParser("MORTALSGROOVE", "MortalsGroove", "en")
internal class MortalsGroove(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MORTALSGROOVE, "mortalsgroove.com") {
	override val postReq = true
}
