package tsuki.site.onemanga.fr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.onemanga.OneMangaParser

@MangaSourceParser("HELLSPARADISESCAN", "HellsParadiseScan", "fr")
internal class HellsParadiseScan(context: MangaLoaderContext) :
	OneMangaParser(context, MangaParserSource.HELLSPARADISESCAN, "hellsparadisescan.com")
