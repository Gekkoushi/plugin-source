package tsuki.site.madara.tr

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@Broken("Site is online but parser is broken — layout/API changed, needs rewrite")
@MangaSourceParser("ESOMANGA", "EsoManga", "tr")
internal class EsoManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.ESOMANGA, "esomanga.com", 10) {
	override val postReq = true
	override val datePattern = "dd/MM/yyyy"
	override val tagPrefix = "manga-kategoriler/"
}