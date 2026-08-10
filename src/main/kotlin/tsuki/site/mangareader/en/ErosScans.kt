package tsuki.site.mangareader.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("EROSSCANS", "ErosScans", "en")
internal class ErosScans(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.EROSSCANS, "scythescans.com", pageSize = 20, searchPageSize = 10)
