package tsuki.site.madara.pt

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("VALKYRIESCAN", "ValkyrieScan", "pt", ContentType.HENTAI)
internal class ValkyrieScan(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.VALKYRIESCAN, "valkyriescan.com", pageSize = 10) {
	override val datePattern: String = "dd/MM/yyyy"
}
