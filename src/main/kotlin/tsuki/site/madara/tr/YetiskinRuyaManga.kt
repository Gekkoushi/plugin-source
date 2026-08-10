package tsuki.site.madara.tr

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@Broken("Domain parked — landing page only, no manga content")
@MangaSourceParser("YETISKINRUYAMANGA", "Yetişkin Rüya Manga", "tr")
internal class YetiskinRuyaManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.YETISKINRUYAMANGA, "www.yetiskinruyamanga.com", 16) {
	override val datePattern = "dD/MM/yyyy"
}
