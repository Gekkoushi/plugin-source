package tsuki.site.madara.es

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("KNIGHTNOSCANLATION", "Lector KNS", "es")
internal class KnightnoScanlation(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.KNIGHTNOSCANLATION, "lectorknight.com") {
	override val listUrl = "sr/"
	override val tagPrefix = "generos/"
}
