package tsuki.site.madara.tr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("ASURASCANSTR", "AsuraScansTR", "tr")
internal class AsuraScansTR(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.ASURASCANSTR, "asurascans.com.tr") {
	override val tagPrefix = "tur/"
}
