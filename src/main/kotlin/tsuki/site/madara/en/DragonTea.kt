package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("DRAGONTEA", "DragonTea", "en")
internal class DragonTea(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.DRAGONTEA, "dragontea.ink") {
	override val datePattern = "MM/dd/yyyy"
}
