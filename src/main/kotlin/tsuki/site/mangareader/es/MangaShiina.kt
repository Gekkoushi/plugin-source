package tsuki.site.mangareader.es

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("MANGASHIINA", "MangaMukai.com", "es")
internal class MangaShiina(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.MANGASHIINA, "mangamukai.com", pageSize = 20, searchPageSize = 10)
