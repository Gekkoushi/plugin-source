package tsuki.site.foolslide.it

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.foolslide.FoolSlideParser

@Broken("Domain parked — landing page only, no manga content")
@MangaSourceParser("POWERMANGA", "PowerManga", "it")
internal class PowerManga(context: MangaLoaderContext) :
	FoolSlideParser(context, MangaParserSource.POWERMANGA, "reader.powermanga.org") {
	override val pagination = false
}
