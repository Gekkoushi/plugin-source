package tsuki.site.madara.pl

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("MANGAHONA", "MangaHona", "pl")
internal class MangaHona(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGAHONA, "mangahona.pl") {
	override val datePattern = "yyyy-MM-dd"
}
