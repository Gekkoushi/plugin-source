package tsuki.site.madara.tr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("DIAMONDFANSUB", "DiamondFansub", "tr")
internal class DiamondFansub(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.DIAMONDFANSUB, "diamondfansub.com", 10) {
	override val datePattern = "d MMMM"
	override val listUrl = "seri/"
	override val tagPrefix = "seri-turu/"
}
