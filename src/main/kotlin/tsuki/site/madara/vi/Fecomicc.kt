package tsuki.site.madara.vi

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser
import tsuki.Broken

@Broken("No longer available")
@MangaSourceParser("FECOMICC", "Fecomic", "vi")
internal class Fecomicc(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.FECOMICC, "mangasup.net", 9) {
	override val listUrl = "comic/"
	override val tagPrefix = "the-loai/"
	override val datePattern = "dd/MM/yyyy"
}
