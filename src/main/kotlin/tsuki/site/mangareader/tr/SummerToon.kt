package tsuki.site.mangareader.tr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("SUMMERTOON", "SummerToon", "tr")
internal class SummerToon(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.SUMMERTOON, "summertoon.co", pageSize = 10, searchPageSize = 10) {
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}

