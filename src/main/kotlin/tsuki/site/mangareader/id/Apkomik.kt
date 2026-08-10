package tsuki.site.mangareader.id

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser
import java.util.Locale

@MangaSourceParser("APKOMIK", "Apkomik", "id")
internal class Apkomik(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.APKOMIK, "apkomik.cc", pageSize = 20, searchPageSize = 10) {
	override val sourceLocale: Locale = Locale.ENGLISH
}
