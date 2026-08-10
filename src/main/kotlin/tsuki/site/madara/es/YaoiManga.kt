package tsuki.site.madara.es

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("YAOIMANGA", "YaoiManga", "es")
internal class YaoiManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.YAOIMANGA, "yaoimanga.es", 42)
