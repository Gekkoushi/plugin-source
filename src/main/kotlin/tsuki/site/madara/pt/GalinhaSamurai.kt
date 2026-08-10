package tsuki.site.madara.pt

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser
import tsuki.Broken

@Broken("Original site closed")
@MangaSourceParser("GALINHASAMURAI", "GalinhaSamurai", "pt")
internal class GalinhaSamurai(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.GALINHASAMURAI, "galinhasamurai.com") {
	override val datePattern = "dd/MM/yyyy"
	override val withoutAjax = true
}
