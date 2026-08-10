package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("MANGAFASTNET", "MangaFast.net", "en")
internal class MangaFastNet(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGAFASTNET, "manhuafast.net") {
	override val withoutAjax = true

}
