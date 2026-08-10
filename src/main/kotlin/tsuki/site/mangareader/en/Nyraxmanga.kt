package tsuki.site.mangareader.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("NYRAXMANGA", "Nyraxmanga", "en")
internal class Nyraxmanga(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.NYRAXMANGA, "nyraxmanga.com", pageSize = 20, searchPageSize = 10)
