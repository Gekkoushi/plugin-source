package tsuki.site.mangabox.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.config.ConfigKey
import tsuki.model.MangaParserSource
import tsuki.site.mangabox.MangaboxParser

@MangaSourceParser("HMANGABAT", "MangaBat", "en")
internal class Mangabat(context: MangaLoaderContext) :
	MangaboxParser(context, MangaParserSource.HMANGABAT) {
	override val configKeyDomain = ConfigKey.Domain("www.mangabats.com")
}
