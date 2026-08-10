package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("MANHUAES", "ManhuaEs", "en")
internal class Manhuaes(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANHUAES, "manhuaes.com") {
	override val postReq = true
}
