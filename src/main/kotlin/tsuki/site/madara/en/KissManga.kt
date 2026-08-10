package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("KISSMANGA", "KissManga", "en")
internal class KissManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.KISSMANGA, "kissmanga.in") {
	override val datePattern = "MMMM dd, yyyy"
	override val listUrl = "mangalist/"
}
