package tsuki.site.madara.tr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("TIMENAIGHT", "TimeNaight", "tr")
internal class Timenaight(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.TIMENAIGHT, "timenaight.org") {
	override val postReq = true
	override val datePattern = "dd/MM/yyyy"
}
