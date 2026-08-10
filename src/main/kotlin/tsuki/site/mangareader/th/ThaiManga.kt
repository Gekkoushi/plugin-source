package tsuki.site.mangareader.th

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("THAIMANGA", "ThaiManga", "th")
internal class ThaiManga(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.THAIMANGA, "www.thaimanga.net", pageSize = 40, searchPageSize = 10) {
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
