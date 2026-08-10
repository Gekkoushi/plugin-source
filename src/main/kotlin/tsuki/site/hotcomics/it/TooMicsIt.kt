package tsuki.site.hotcomics.it

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.hotcomics.HotComicsParser

@MangaSourceParser("TOOMICSIT", "TooMicsIt", "it")
internal class TooMicsIt(context: MangaLoaderContext) :
	HotComicsParser(context, MangaParserSource.TOOMICSIT, "toomics.com/it") {
	override val isSearchSupported = false
	override val mangasUrl = "/webtoon/ranking/genre"
	override val selectMangas = "li > div.visual"
	override val selectMangaChapters = "li.normal_ep:has(.coin-type1)"
	override val selectTagsList = "div.genre_list li:not(.on) a"
	override val selectPages = "div[id^=load_image_] img"
	override val onePage = true
}
