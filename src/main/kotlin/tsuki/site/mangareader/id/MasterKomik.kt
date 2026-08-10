package tsuki.site.mangareader.id

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("MASTERKOMIK", "Tenshi", "id")
internal class MasterKomik(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.MASTERKOMIK, "tenshi01.id", pageSize = 20, searchPageSize = 20) {
	override val datePattern = "MMM d, yyyy"
	override val listUrl = "/komik"
}
