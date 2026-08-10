package tsuki.site.foolslide.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.foolslide.FoolSlideParser

@MangaSourceParser("DEATHTOLLSCANS", "DeathTollScans", "en")
internal class Deathtollscans(context: MangaLoaderContext) :
	FoolSlideParser(context, MangaParserSource.DEATHTOLLSCANS, "reader.deathtollscans.net", 26)
