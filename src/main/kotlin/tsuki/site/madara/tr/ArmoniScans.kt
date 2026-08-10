package tsuki.site.madara.tr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("ARMONISCANS", "ArmoniScans", "tr")
internal class ArmoniScans(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.ARMONISCANS, "armoniscans.net") {
	override val tagPrefix = "tur/"
}
