package tsuki.site.mangareader.id

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser
import java.util.*

@MangaSourceParser("MANHWAINDOICU", "KomikCinta", "id", ContentType.HENTAI)
internal class ManhwaIndoIcu(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.MANHWAINDOICU, "komikdewasa.art", pageSize = 30, searchPageSize = 10) {
	override val sourceLocale: Locale = Locale.ENGLISH
	override val listUrl = "/komik"
}
