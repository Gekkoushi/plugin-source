package tsuki.site.mangareader.es

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("BYMICHIBY", "Bymichiby", "es", ContentType.HENTAI)
internal class Bymichiby(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.BYMICHIBY, "bymichiby.com", pageSize = 20, searchPageSize = 10)
