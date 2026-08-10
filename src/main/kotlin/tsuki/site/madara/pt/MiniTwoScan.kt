package tsuki.site.madara.pt

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.*
import tsuki.site.madara.MadaraParser

@MangaSourceParser("MINITWOSCAN", "MiniTwoScan", "pt")
internal class MiniTwoScan(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MINITWOSCAN, "minitwoscan.com") {
	override val withoutAjax = true
	override val postReq = true
}
