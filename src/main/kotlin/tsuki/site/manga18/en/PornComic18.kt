package tsuki.site.manga18.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.manga18.Manga18Parser

@MangaSourceParser("PORNCOMIC18", "18PornComic", "en", ContentType.HENTAI)
internal class PornComic18(context: MangaLoaderContext) :
	Manga18Parser(context, MangaParserSource.PORNCOMIC18, "18porncomic.com") {
	override val selectTag = "div.item:not(.info_label) div.info_value a"
}
