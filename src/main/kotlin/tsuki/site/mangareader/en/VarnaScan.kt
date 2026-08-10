package tsuki.site.mangareader.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("VARNASCAN", "VarnaScan", "en")
internal class VarnaScan(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.VARNASCAN, "varnascan.xyz", pageSize = 20, searchPageSize = 10)
