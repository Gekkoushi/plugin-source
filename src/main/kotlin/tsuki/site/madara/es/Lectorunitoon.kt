package tsuki.site.madara.es

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@Broken("Domain parked — landing page only, no manga content")
@MangaSourceParser("LECTORUNITOON", "LectoruniToon", "es")
internal class Lectorunitoon(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.LECTORUNITOON, "lectorunitoon.com", 10) {
	override val tagPrefix = "generos/"
	override val datePattern = "dd/MM/yyyy"
}
