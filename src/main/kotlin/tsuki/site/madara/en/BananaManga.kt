package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("BANANA_MANGA", "BananaManga", "en")
internal class BananaManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.BANANA_MANGA, "bananamanga.net", 20)
