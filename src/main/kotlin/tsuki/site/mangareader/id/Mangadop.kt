package tsuki.site.mangareader.id

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser
import java.util.*

@MangaSourceParser("MANGADOP", "MangaDop", "id", ContentType.HENTAI)
internal class Mangadop(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.MANGADOP, "mangadop.net", pageSize = 20, searchPageSize = 20) {
	override val sourceLocale: Locale = Locale.ENGLISH
}
