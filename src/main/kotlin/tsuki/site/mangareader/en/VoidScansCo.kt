package tsuki.site.mangareader.en

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@Broken("Domain hijacked — now serves a JS redirect to spam/ads")
@MangaSourceParser("VOIDSCANS_CO", "VoidScans", "en")
internal class VoidScansCo(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.VOIDSCANS_CO, "voidscans.co", pageSize = 30, searchPageSize = 42)
