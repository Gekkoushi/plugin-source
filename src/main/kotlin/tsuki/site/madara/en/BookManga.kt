package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("BOOKMANGA", "BookManga", "en")
internal class BookManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.BOOKMANGA, "bookmanga.com", 20)
