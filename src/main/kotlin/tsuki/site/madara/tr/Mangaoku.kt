package tsuki.site.madara.tr

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@Broken("Domain has no DNS records — site is gone")
@MangaSourceParser("MANGAOKU", "Mangaoku", "tr")
internal class Mangaoku(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGAOKU, "mangaoku.info", 24) {
	override val datePattern = "dd MMMM yyyy"
	override val listUrl = "seri/"
	override val tagPrefix = "tur/"
}
