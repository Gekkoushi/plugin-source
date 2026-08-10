package tsuki.site.madara.ar

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("GMANGA", "Gmanga", "ar")
internal class Gmanga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.GMANGA, "gmanga.site")
