package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("TOPMANHUA", "ManhuaTop", "en")
internal class TopManhua(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.TOPMANHUA, "manhuatop.org") {
	override val tagPrefix = "manhua-genre/"
	override val listUrl = "manhua/"
	override val datePattern = "MM/dd/yyyy"
}
