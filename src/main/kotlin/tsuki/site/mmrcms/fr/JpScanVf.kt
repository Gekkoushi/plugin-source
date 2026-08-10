package tsuki.site.mmrcms.fr

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.mmrcms.MmrcmsParser
import java.util.*

@Broken("Site is online but parser is broken — layout/API changed, needs rewrite")
@MangaSourceParser("JPSCANVF", "LireScanVf.com", "fr")
internal class JpScanVf(context: MangaLoaderContext) :
	MmrcmsParser(context, MangaParserSource.JPSCANVF, "lirescanvf.com") {
	override val sourceLocale: Locale = Locale.ENGLISH
}
