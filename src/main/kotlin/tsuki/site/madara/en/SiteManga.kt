package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("SITEMANGA", "SiteManga", "en")
internal class SiteManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.SITEMANGA, "sitemanga.com") {
	override val datePattern = "MM/dd/yyyy"
}
