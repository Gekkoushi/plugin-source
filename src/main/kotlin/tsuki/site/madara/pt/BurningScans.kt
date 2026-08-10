package tsuki.site.madara.pt

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@Broken("Domain has no DNS records — site is gone")
@MangaSourceParser("BURNINGSCANS", "BurningScans", "pt")
internal class BurningScans(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.BURNINGSCANS, "burningscans.com") {
	override val datePattern = "dd/MM/yyyy"
}
