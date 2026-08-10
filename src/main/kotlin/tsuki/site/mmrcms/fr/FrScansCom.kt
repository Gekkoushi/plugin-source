package tsuki.site.mmrcms.fr

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.mmrcms.MmrcmsParser
import java.util.*

@Broken("Domain hijacked — now serves a JS redirect to spam/ads")
@MangaSourceParser("FRSCANSCOM", "FrScans.com", "fr")
internal class FrScansCom(context: MangaLoaderContext) :
	MmrcmsParser(context, MangaParserSource.FRSCANSCOM, "frscans.com") {
	override val sourceLocale: Locale = Locale.ENGLISH
}
