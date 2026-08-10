package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("FIRSTKISSMANHUA", "FirstKissManhua", "en")
internal class FirstKissManhua(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.FIRSTKISSMANHUA, "1stkissmanhua.net", 20) {
	override val listUrl = "manhua/"
}
