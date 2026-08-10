package tsuki.site.madara.pt

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("PASSAMAOSCAN", "PassamaoScan", "pt")
internal class PassamaoScan(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.PASSAMAOSCAN, "passamaoscan.com") {
	override val datePattern: String = "dd/MM/yyyy"
}
