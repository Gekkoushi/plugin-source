package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("MANGAREADCO", "MangaRead.co", "en")
internal class MangaReadCo(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGAREADCO, "mangaread.co", 16) {
	override val tagPrefix = "m-genre/"
	override val datePattern = "yyyy-MM-dd"
}
