package tsuki.site.mangareader.th

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser
import tsuki.Broken

@Broken("Original site closed")
@MangaSourceParser("SOMANGA", "SoManga", "th")
internal class SoManga(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.SOMANGA, "so-manga.com", pageSize = 5, searchPageSize = 25)
