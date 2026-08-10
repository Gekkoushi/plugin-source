package tsuki.site.mangareader.fr

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@Broken("Site is online but parser is broken — layout/API changed, needs rewrite")
@MangaSourceParser("RIMUSCANS", "RimuScans", "fr")
internal class RimuScans(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.RIMUSCANS, "rimuscans.com", pageSize = 30, searchPageSize = 10)
