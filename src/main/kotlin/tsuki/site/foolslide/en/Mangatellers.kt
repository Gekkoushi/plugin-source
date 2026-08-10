package tsuki.site.foolslide.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.foolslide.FoolSlideParser

@MangaSourceParser("MANGATELLERS", "Mangatellers", "en")
internal class Mangatellers(context: MangaLoaderContext) :
	FoolSlideParser(context, MangaParserSource.MANGATELLERS, "reader.mangatellers.gr") {
	override val pagination = false
}
