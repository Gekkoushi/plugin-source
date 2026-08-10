package tsuki.site.madara.tr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("MANGATR", "MangaTr", "tr")
internal class MangaTr(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGATR, "mangatr.app") {
	override val tagPrefix = "tur/"
}
