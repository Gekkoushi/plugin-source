package tsuki.site.mangareader.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("MANHUASCANUS", "ManhuaScan.Us", "en", ContentType.HENTAI)
internal class ManhuaScanUs(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.MANHUASCANUS, "manhuascan.us", pageSize = 30, searchPageSize = 30) {
	override val datePattern = "dd-MM-yyyy"
	override val listUrl = "/manga-list"
}
