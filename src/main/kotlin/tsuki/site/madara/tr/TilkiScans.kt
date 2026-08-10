package tsuki.site.madara.tr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("TILKISCANS", "TilkiScans", "tr")
internal class TilkiScans(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.TILKISCANS, "www.tilkiscans.com", pageSize = 18) {
	override val datePattern = "dd/MM/yyyy"
}
