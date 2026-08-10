package tsuki.site.madara.pt

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("NOINDEXSCAN", "NoindexScan", "pt", ContentType.HENTAI)
internal class NoindexScan(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.NOINDEXSCAN, "noindexscan.com") {
	override val datePattern: String = "dd/MM/yyyy"
}
