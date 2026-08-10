package tsuki.site.madara.pt

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("IMPERIODABRITANNIA", "ImperioDaBritannia", "pt")
internal class ImperiodaBritannia(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.IMPERIODABRITANNIA, "imperiodabritannia.com", 10) {
	override val datePattern: String = "dd 'de' MMMMM 'de' yyyy"
	override val withoutAjax = true
}
