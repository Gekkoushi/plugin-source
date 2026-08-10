package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("MANHUAUSS", "Manhuauss", "en")
internal class Manhuauss(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANHUAUSS, "manhuaus.com") {
	override val withoutAjax = true
}
