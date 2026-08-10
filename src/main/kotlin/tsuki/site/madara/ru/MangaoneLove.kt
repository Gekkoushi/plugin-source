package tsuki.site.madara.ru

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("MANGAONELOVE", "MangaOneLove", "ru")
internal class MangaoneLove(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGAONELOVE, "mangaonelove.site", 10) {
	override val datePattern = "dd.MM.yyyy"
	override val postReq = true
}
