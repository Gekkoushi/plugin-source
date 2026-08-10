package tsuki.site.mangareader.es

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("MIAUSCAN", "LectorMiau", "es")
internal class MiauScan(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.MIAUSCAN, "leemiau.com", pageSize = 20, searchPageSize = 10)
