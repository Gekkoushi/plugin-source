package tsuki.site.madara.pt

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("WINTERSCAN", "WinterScan", "pt")
internal class WinterScan(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.WINTERSCAN, "winterscan.com", pageSize = 20) {
	override val datePattern: String = "dd 'de' MMMMM 'de' yyyy"
}
