package tsuki.site.mangareader.id

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("KOMIKLOKAL", "KomikLokal", "id", ContentType.HENTAI)
internal class KomikLokalParser(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.KOMIKLOKAL, "komikmu.top", pageSize = 20, searchPageSize = 10) {
	override val listUrl = "/komik"
	override val datePattern = "dd/MM/yyyy"
}
