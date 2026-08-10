package tsuki.site.mangareader.en

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@Broken("Domain manhwafreak.xyz is parked / for sale on GoDaddy")
@MangaSourceParser("MANHWA_FREAK", "ManhwaFreak", "en")
internal class ManhwaFreak(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.MANHWA_FREAK, "manhwafreak.xyz", pageSize = 30, searchPageSize = 42)
