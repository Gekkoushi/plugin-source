package tsuki.site.hotcomics.ja

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.hotcomics.HotComicsParser

@MangaSourceParser("TOOMICSJA", "TooMicsJa", "ja")
internal class TooMicsJa(context: MangaLoaderContext) :
	HotComicsParser(context, MangaParserSource.TOOMICSJA, "toomics.com/ja") {
	override val isSearchSupported = false
	override val mangasUrl = "/webtoon/ranking/genre"
	override val selectMangas = "li > div.visual"
	override val selectMangaChapters = "li.normal_ep:has(.coin-type1)"
	override val selectTagsList = "div.genre_list li:not(.on) a"
	override val selectPages = "div[id^=load_image_] img"
	override val onePage = true
}
