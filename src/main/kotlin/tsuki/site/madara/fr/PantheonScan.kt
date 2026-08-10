package tsuki.site.madara.fr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("PANTHEONSCAN", "PantheonScan.com", "fr")
internal class PantheonScan(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.PANTHEONSCAN, "pantheon-scan.com") {
	override val datePattern = "d MMMM yyyy"
}
