package tsuki.site.mangareader.tr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("NOXSUBS", "NoxSubs", "tr")
internal class NoxSubs(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.NOXSUBS, "noxsubs.net", pageSize = 20, searchPageSize = 10)
