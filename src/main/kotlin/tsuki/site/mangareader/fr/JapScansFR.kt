package tsuki.site.mangareader.fr

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser
import java.util.Locale

@Broken("Domain hijacked — now serves a JS redirect to spam/ads")
@MangaSourceParser("JAPSCANSFR", "JapScans.fr", "fr")
internal class JapScansFR(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.JAPSCANSFR, "japscans.fr", pageSize = 20, searchPageSize = 10) {
	override val sourceLocale: Locale = Locale.ENGLISH
}
