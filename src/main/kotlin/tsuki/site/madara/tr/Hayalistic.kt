package tsuki.site.madara.tr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("HAYALISTIC", "Hayalistic", "tr")
internal class Hayalistic(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.HAYALISTIC, "hayalistic.com.tr", 24) {
	override val datePattern = "dd/MM/yyyy"
}
