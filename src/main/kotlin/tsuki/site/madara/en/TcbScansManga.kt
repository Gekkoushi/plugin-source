package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("TCBSCANSMANGA", "TcbScansManga", "en")
internal class TcbScansManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.TCBSCANSMANGA, "tcbscans-manga.com", 10) {
	override val selectPage = "img"
}
