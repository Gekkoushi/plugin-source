package tsuki.site.mangareader.tr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser
import tsuki.Broken

@Broken("Domain has no DNS records — site is gone")
@MangaSourceParser("PRUNUSSCANS", "PrunusScans", "tr")
internal class PrunusScans(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.PRUNUSSCANS, "prunusscans.com", pageSize = 20, searchPageSize = 10)
