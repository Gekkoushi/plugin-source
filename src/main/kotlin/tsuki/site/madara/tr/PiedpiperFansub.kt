package tsuki.site.madara.tr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("PIEDPIPERFANSUB", "PiedpiperFansub", "tr")
internal class PiedpiperFansub(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.PIEDPIPERFANSUB, "piedpiperfansub.me", 18) {
	override val datePattern = "d MMMM yyyy"
}
