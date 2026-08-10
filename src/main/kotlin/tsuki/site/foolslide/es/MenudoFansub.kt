package tsuki.site.foolslide.es

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.foolslide.FoolSlideParser

@MangaSourceParser("MENUDO_FANSUB", "Menudo Fansub", "es")
internal class MenudoFansub(context: MangaLoaderContext) :
	FoolSlideParser(context, MangaParserSource.MENUDO_FANSUB, "www.menudo-fansub.com", 25) {
	override val searchUrl = "slide/search/"
	override val listUrl = "slide/directory/"
}
