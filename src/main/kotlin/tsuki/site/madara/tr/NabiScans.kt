package tsuki.site.madara.tr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser
import tsuki.Broken

@Broken("HTTP 403 — access blocked")
@MangaSourceParser("NABISCANS", "NabiScans", "tr")
internal class NabiScans(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.NABISCANS, "nabiscans.com") {
	override val datePattern = "d MMMM yyyy"
}
