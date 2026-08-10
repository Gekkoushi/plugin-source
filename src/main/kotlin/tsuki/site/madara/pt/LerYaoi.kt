package tsuki.site.madara.pt

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("LERYAOI", "LerYaoi", "pt", ContentType.HENTAI)
internal class LerYaoi(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.LERYAOI, "leryaoi.com", 10) {
	override val datePattern = "dd/MM/yyyy"
	override val listUrl = "bl/"
	override val tagPrefix = "genero/"
}
