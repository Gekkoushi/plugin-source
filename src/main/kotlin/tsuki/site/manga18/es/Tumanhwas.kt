package tsuki.site.manga18.es

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.manga18.Manga18Parser

@MangaSourceParser("TUMANHWAS", "Tumanhwas", "es", ContentType.HENTAI)
internal class Tumanhwas(context: MangaLoaderContext) :
	Manga18Parser(context, MangaParserSource.TUMANHWAS, "tumanhwas.club") {
	override val selectTag = "div.item:contains(Géneros) div.info_value a"
	override val selectAlt = "div.item:contains(Títulos alternativos) div.info_value"
}
