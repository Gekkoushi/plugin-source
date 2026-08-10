package tsuki.site.madara.tr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("MANGASEHRI", "MangaSehri.com", "tr")
internal class Mangasehri(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGASEHRI, "manga-sehri.com", 18) {
	override val datePattern = "dd/MM/yyyy"
}
