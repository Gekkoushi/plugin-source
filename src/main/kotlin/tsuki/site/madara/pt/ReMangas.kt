package tsuki.site.madara.pt

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("REMANGAS", "ReMangas", "pt")
internal class ReMangas(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.REMANGAS, "remangas.net") {
	override val datePattern = "dd/MM/yyyy"
}
