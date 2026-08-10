package tsuki.site.zeistmanga.es

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.zeistmanga.ZeistMangaParser

@MangaSourceParser("DATGARSCANLATION", "DatgarScanlation", "es")
internal class DatgarScanlation(context: MangaLoaderContext) :
	ZeistMangaParser(context, MangaParserSource.DATGARSCANLATION, "datgarscanlation.blogspot.com")
