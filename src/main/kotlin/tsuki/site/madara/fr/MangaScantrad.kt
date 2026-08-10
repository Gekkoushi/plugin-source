package tsuki.site.madara.fr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("MANGA_SCANTRAD", "MangaScantrad.io", "fr")
internal class MangaScantrad(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGA_SCANTRAD, "manga-scantrad.io") {
	override val datePattern = "d MMMM yyyy"
}
