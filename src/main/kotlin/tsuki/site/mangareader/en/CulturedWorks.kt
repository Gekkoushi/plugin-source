package tsuki.site.mangareader.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("CULTUREDWORKS", "CulturedWorks", "en")
internal class CulturedWorks(context: MangaLoaderContext) :
	MangaReaderParser(
		context,
		MangaParserSource.CULTUREDWORKS,
		"culturedworks.com",
		pageSize = 20,
		searchPageSize = 10,
	) {
	override val selectMangaList = ".listupd .bs .bsx"
}
