package tsuki.site.madara.pt

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("MANGAONLINE_BLOG", "MangaOnline", "pt")
internal class MangaOnline(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGAONLINE_BLOG, "mangaonline.blog", 16) {
	override val datePattern: String = "dd 'de' MMMMM 'de' yyyy"
}
