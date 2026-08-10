package tsuki.site.mangabox.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.config.ConfigKey
import tsuki.model.MangaParserSource
import tsuki.site.mangabox.MangaboxParser

@MangaSourceParser("MANGAKAKALOT", "Mangakakalot.gg", "en")
internal class Mangakakalot(context: MangaLoaderContext) :
	MangaboxParser(context, MangaParserSource.MANGAKAKALOT) {
	override val configKeyDomain = ConfigKey.Domain("www.mangakakalot.gg")
}
