package tsuki.site.madara.en

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@Broken("Site is online but parser is broken — layout/API changed, needs rewrite")
@MangaSourceParser("ANSHSCANS", "AnshScans", "en")
internal class AnshScans(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.ANSHSCANS, "anshscans.org", 10) {
	override val tagPrefix = "genre/"
	override val datePattern = "MMMM dd, yyyy"
}
