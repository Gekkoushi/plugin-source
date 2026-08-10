package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("SECTSCANS", "SectScans", "en")
internal class SectScans(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.SECTSCANS, "sectscans.com") {
	override val listUrl = "comics/"
}
