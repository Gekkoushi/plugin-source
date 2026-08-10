package tsuki.site.mangareader.tr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("SEREINSCAN", "SereinScan", "tr")
internal class SereinScan(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.SEREINSCAN, "sereinscan.com", pageSize = 20, searchPageSize = 10)
