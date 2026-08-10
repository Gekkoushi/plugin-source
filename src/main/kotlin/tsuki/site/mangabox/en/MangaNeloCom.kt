package tsuki.site.mangabox.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.config.ConfigKey
import tsuki.model.MangaParserSource
import tsuki.site.mangabox.MangaboxParser

@MangaSourceParser("MANGANELO_COM", "MangaNelo.com", "en")
internal class MangaNeloCom(context: MangaLoaderContext) :
	MangaboxParser(context, MangaParserSource.MANGANELO_COM) {
	override val configKeyDomain = ConfigKey.Domain("www.nelomanga.net")
}
