package tsuki.site.madara.tr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("ARAZNOVEL", "ArazNovel", "tr")
internal class ArazNovel(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.ARAZNOVEL, "araznovel.com", 10) {
	override val datePattern = "d MMMM yyyy"
	override val postReq = true
}
