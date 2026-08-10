package tsuki.site.mmrcms.fr

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.mmrcms.MmrcmsParser
import java.util.*

@Broken("Blocked by Cloudflare challenge")
@MangaSourceParser("BENTOSCAN", "BentoScan", "fr")
internal class BentoScan(context: MangaLoaderContext) :
	MmrcmsParser(context, MangaParserSource.BENTOSCAN, "bentoscan.com") {
	override val sourceLocale: Locale = Locale.ENGLISH
	override val imgUpdated = ".jpg"
}
