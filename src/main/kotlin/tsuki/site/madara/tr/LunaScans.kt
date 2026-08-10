package tsuki.site.madara.tr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("LUNASCANS", "LunaScans", "tr", ContentType.HENTAI)
internal class LunaScans(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.LUNASCANS, "tuhafscans.com") {
	override val postReq = true
	override val datePattern = "dd MMMM yyyy"
}
