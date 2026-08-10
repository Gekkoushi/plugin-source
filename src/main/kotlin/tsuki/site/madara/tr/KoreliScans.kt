package tsuki.site.madara.tr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("KORELISCANS", "KoreliScans", "tr")
internal class KoreliScans(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.KORELISCANS, "koreliscans.com", 10) {
	override val datePattern = "d MMMM"
}
