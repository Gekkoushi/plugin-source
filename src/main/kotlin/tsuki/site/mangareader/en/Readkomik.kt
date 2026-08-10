package tsuki.site.mangareader.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("READKOMIK", "ReadKomik", "en")
internal class Readkomik(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.READKOMIK, "novelstreams.com", pageSize = 20, searchPageSize = 20) {
	override val datePattern = "MMM d, yyyy"
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}

