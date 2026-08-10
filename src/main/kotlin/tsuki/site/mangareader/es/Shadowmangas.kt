package tsuki.site.mangareader.es

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("SHADOWMANGAS", "ShadowMangas", "es")
internal class Shadowmangas(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.SHADOWMANGAS, "shadowmangas.com", pageSize = 10, searchPageSize = 10) {
	override val datePattern = "MMM d, yyyy"
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
