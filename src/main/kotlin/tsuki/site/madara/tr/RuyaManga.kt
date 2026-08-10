package tsuki.site.madara.tr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("RUYAMANGA", "RuyaManga", "tr")
internal class RuyaManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.RUYAMANGA, "ruyamanga.net", 18) {
	override val tagPrefix = "manga-kategori/"
	override val datePattern = "dd/MM/yyyy"
}
