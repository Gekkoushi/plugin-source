package tsuki.site.heancmsalt.es

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.heancmsalt.HeanCmsAlt

@Broken("Not dead, changed template")
@MangaSourceParser("MANGAESP", "MangaEsp", "es")
internal class MangaEsp(context: MangaLoaderContext) :
	HeanCmsAlt(context, MangaParserSource.MANGAESP, "mangaesp.topmanhuas.org", 15) {

	override val listUrl = "/comic"

	override val selectManga = "div.contenedor div.grid-5  .p-relative:not(.portada-contenedor)"
	override val selectMangaTitle = "div.titulo-contenedor"

	override val selectDesc = "div.project-sinopsis-contenido"
	override val selectAlt = "div.project-info-opcion:contains(Altenativo) div.project-info-contenido"
	override val selectChapter = "div.grid-capitulos div a"
	override val selectChapterTitle = ".capitulo-info-titulo"
	override val selectChapterDate = ".capitulo-info-fecha"

	override val selectPage = ".grid-center img"
}
