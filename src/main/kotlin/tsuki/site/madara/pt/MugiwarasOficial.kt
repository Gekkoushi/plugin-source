package tsuki.site.madara.pt

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("MUGIWARASOFICIAL", "MugiwarasOficial", "pt")
internal class MugiwarasOficial(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MUGIWARASOFICIAL, "mugiwarasoficial.com") {
	override val datePattern: String = "dd/MM/yyyy"
}
