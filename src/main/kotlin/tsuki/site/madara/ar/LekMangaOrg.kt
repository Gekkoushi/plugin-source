package tsuki.site.madara.ar

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("LEKMANGAORG", "LekManga.org", "ar")
internal class LekMangaOrg(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.LEKMANGAORG, "lekmanga.org", pageSize = 10) {
	override val listUrl = "readcomics/"
}
