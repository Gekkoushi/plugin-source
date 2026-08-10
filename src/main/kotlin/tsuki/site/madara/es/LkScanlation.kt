package tsuki.site.madara.es

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@Broken("Domain parked — landing page only, no manga content")
@MangaSourceParser("LKSCANLATION", "LkScanlation", "es")
internal class LkScanlation(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.LKSCANLATION, "lkscanlation.com") {
	override val tagPrefix = "manhwa-genre/"
	override val listUrl = "manhwa/"
}
