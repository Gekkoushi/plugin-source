package tsuki.site.mangareader.id

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("KOMIKMAMA", "KomikMama", "id")
internal class KomikMama(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.KOMIKMAMA, "komikmama.lat", pageSize = 30, searchPageSize = 10) {
	override val listUrl = "/komik"
}
