package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("MANHWACLAN", "ManhwaClan", "en")
internal class ManhwaClan(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANHWACLAN, "manhwaclan.com", pageSize = 10) {
	override val datePattern = "MMMM dd, yyyy"
}
