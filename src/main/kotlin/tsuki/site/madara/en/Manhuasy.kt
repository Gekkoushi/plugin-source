package tsuki.site.madara.en

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@Broken("Site is online but parser is broken — layout/API changed, needs rewrite")
@MangaSourceParser("MANHUASY", "ManhuaSy", "en")
internal class Manhuasy(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANHUASY, "www.manhuasy.com") {
	override val listUrl = "manhua/"
	override val tagPrefix = "manhua-genre/"
}
