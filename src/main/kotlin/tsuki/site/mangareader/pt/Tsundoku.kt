package tsuki.site.mangareader.pt

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("TSUNDOKU", "Tsundoku", "pt")
internal class Tsundoku(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.TSUNDOKU, "tsundoku.com.br", pageSize = 50, searchPageSize = 50) {
	override val datePattern = "MMM d, yyyy"
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
