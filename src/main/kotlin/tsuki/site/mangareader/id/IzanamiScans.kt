package tsuki.site.mangareader.id

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("IZANAMISCANS", "IzanamiScans", "id")
internal class IzanamiScans(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.IZANAMISCANS, "izanamiscans.my.id", pageSize = 20, searchPageSize = 10)
