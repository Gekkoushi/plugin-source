package tsuki.site.madara.es

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("BARMANGA", "BarManga", "es")
internal class BarManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.BARMANGA, "barmanga.com") {
	override val datePattern = "MM/dd/yyyy"
}
