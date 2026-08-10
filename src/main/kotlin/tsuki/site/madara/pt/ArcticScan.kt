package tsuki.site.madara.pt

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("ARCTICSCAN", "ArcticScan", "pt")
internal class ArcticScan(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.ARCTICSCAN, "arcticscan.top") {
	override val datePattern: String = "yyyy-MM-dd"
}
