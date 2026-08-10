package tsuki.site.mangareader.id

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("MANHWAKU", "Manhwaku", "id")
internal class Manhwaku(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.MANHWAKU, "manhwaku.id", pageSize = 20, searchPageSize = 10)
