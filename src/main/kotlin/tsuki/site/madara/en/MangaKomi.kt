package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("MANGA_KOMI", "MangaKomi", "en")
internal class MangaKomi(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGA_KOMI, "mangakomi.io", pageSize = 18) {
	override val datePattern = "MMMM dd, yyyy"
}
