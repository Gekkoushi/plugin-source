package tsuki.site.mangareader.th

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("TANUKIMANGA", "TanukiManga", "th")
internal class TanukiManga(context: MangaLoaderContext) :
	MangaReaderParser(
		context,
		MangaParserSource.TANUKIMANGA,
		"www.tanuki-manga.com",
		pageSize = 40,
		searchPageSize = 10,
	) {
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
