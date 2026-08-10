package tsuki.site.madara.es

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("MANTRAZSCAN", "MantrazScan", "es")
internal class MantrazScan(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANTRAZSCAN, "mantrazscan.org") {
	override val datePattern = "dd/MM/yyyy"
	override val tagPrefix = "generos-de-manga/"
}
