package tsuki.site.madara.pt

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("LIMBOSCAN", "LimboScan", "pt")
internal class LimboScan(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.LIMBOSCAN, "limboscan.com.br") {
	override val tagPrefix = "obras-genre/"
	override val listUrl = "obras/"
	override val datePattern: String = "dd/MM/yyyy"
}
