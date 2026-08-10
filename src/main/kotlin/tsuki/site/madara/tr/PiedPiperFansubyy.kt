package tsuki.site.madara.tr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("PIEDPIPERFANSUBYY", "PiedPiperFansubyy", "tr", ContentType.HENTAI)
internal class PiedPiperFansubyy(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.PIEDPIPERFANSUBYY, "piedpiperfansubyy.me", 18) {
	override val datePattern = "d MMMM yyyy"
}
