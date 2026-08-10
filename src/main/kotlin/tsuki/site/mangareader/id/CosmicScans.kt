package tsuki.site.mangareader.id

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser
import java.util.*

@MangaSourceParser("COSMIC_SCANS", "CosmicScans.id", "id")
internal class CosmicScans(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.COSMIC_SCANS, "lc4.cosmicscans.asia", pageSize = 30, searchPageSize = 30) {
	override val sourceLocale: Locale = Locale.ENGLISH

	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
