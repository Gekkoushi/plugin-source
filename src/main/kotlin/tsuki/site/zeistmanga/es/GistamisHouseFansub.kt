package tsuki.site.zeistmanga.es

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.zeistmanga.ZeistMangaParser

@MangaSourceParser("GISTAMISHOUSEFANSUB", "GistamisHouseFansub", "es")
internal class GistamisHouseFansub(context: MangaLoaderContext) :
	ZeistMangaParser(context, MangaParserSource.GISTAMISHOUSEFANSUB, "gistamishousefansub.blogspot.com") {
	override val sateOngoing: String = "Activo"
	override val sateFinished: String = "Completo"
	override val sateAbandoned: String = "Cancelado"
	override val selectPage = ".post img"
}
