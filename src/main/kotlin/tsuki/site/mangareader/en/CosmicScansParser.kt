package tsuki.site.mangareader.en

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@Broken("Site is online but parser is broken — layout/API changed, needs rewrite")
@MangaSourceParser("COSMICSCANS", "CosmicScans.com", "en")
internal class CosmicScansParser(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.COSMICSCANS, "cosmic-scans.com", pageSize = 20, searchPageSize = 10) {
	override val datePattern = "MMM d, yyyy"
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
