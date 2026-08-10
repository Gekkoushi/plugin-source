package tsuki.site.mangareader.id

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("KOMIKGO", "KomikGo", "id", ContentType.HENTAI)
internal class KomikGo(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.KOMIKGO, "komikgo.xyz", pageSize = 20, searchPageSize = 10)
