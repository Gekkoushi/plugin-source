package tsuki.site.mangareader.ar

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("UMIMANGA", "UmiManga", "ar")
internal class UmiManga(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.UMIMANGA, "www.umimanga.com", pageSize = 20, searchPageSize = 10) {
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
