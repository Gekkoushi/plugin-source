package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("MANHUAFAST", "ManhuaFast", "en")
internal class Manhuafast(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANHUAFAST, "manhuafast.com")
