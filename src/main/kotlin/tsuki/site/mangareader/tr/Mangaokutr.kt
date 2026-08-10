package tsuki.site.mangareader.tr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("MANGAOKUTR", "MangaOkuTr", "tr")
internal class Mangaokutr(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.MANGAOKUTR, "mangaokutr.net", pageSize = 25, searchPageSize = 20)
