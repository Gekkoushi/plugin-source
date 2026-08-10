package tsuki.site.mangareader.id

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser
import java.util.*

@Broken("Blocked by Cloudflare challenge")
@MangaSourceParser("OTSUGAMI", "Otsugami", "id")
internal class Otsugami(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.OTSUGAMI, "otsugami.id", pageSize = 40, searchPageSize = 10) {
	override val sourceLocale: Locale = Locale.ENGLISH

}
