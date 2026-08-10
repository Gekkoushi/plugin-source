package tsuki.site.madara.pt

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("DIANXIATRADS", "DianxiaTrads", "pt")
internal class DianxiaTrads(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.DIANXIATRADS, "dianxiatrads.com", 10) {
	override val datePattern: String = "dd/MM/yyyy"
}
