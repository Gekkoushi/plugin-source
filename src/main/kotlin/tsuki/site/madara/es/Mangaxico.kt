package tsuki.site.madara.es

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("MANGAXICO", "MangaXico", "es", ContentType.HENTAI)
internal class Mangaxico(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGAXICO, "mangagojo18.com", 24)
