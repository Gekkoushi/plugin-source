package tsuki.site.mangareader.id

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser
import java.util.*

@MangaSourceParser("NGOMIK", "Ngomik", "id")
internal class Ngomik(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.NGOMIK, "ngomik.mom", pageSize = 20, searchPageSize = 5) {
	override val sourceLocale: Locale = Locale.ENGLISH
}
