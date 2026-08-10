package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("AQUAMANGA", "AquaManga", "en")
internal class AquaManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.AQUAMANGA, "aquareader.net", 20) {
	override val withoutAjax = true
}
