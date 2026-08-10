package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("NOVELMIC", "NovelMic", "en")
internal class Novelmic(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.NOVELMIC, "novelmic.com", 20) {
	override val postReq = true
}
