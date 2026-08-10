package tsuki.site.madara.es

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("MI2MANGAES", "Mi2MangaEs", "es")
internal class Mi2MangaEs(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MI2MANGAES, "mi2manga.lat", 10)
