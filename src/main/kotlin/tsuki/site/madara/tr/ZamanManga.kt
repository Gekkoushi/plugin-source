package tsuki.site.madara.tr

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@Broken("Domain parked — landing page only, no manga content")
@MangaSourceParser("ZAMANMANGA", "ZamanManga", "tr")
internal class ZamanManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.ZAMANMANGA, "zamanmanga.com") {
	override val datePattern = "dd MMMM yyyy"
}