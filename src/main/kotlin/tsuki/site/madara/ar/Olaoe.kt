package tsuki.site.madara.ar

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("OLAOE", "Olaoe", "ar")
internal class Olaoe(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.OLAOE, "olaoe.cyou") {
	override val datePattern = "dd-MM-yyyy"
	override val tagPrefix = "/شوجو"
	override val listUrl = "works/"
}
