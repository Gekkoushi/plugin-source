package tsuki.site.mangareader.th

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("LAMIMANGA", "LamiManga", "th")
internal class LamiManga(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.LAMIMANGA, "mangalami.com", pageSize = 20, searchPageSize = 10) {
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
