package tsuki.site.madara.ar

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("MANGASPARK", "Manga-Spark", "ar")
internal class Mangaspark(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGASPARK, "sparkmanga.net", pageSize = 10) {
	override val postReq = true
	override val datePattern = "d MMMM، yyyy"
	override val selectPage = "div.page-break, div.page-box, div.no-gaps"
}
