package tsuki.site.mangareader.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser
import tsuki.Broken

@Broken("Site is online but parser is broken — layout/API changed, needs rewrite")
@MangaSourceParser("SPIDERSCANS", "SpiderScans", "en")
internal class SpiderScans(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.SPIDERSCANS, "spiderscans.xyz", pageSize = 20, searchPageSize = 10)
