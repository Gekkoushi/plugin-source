package tsuki.site.mangareader.id

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("MANGAKYO", "MangaKyo", "id")
internal class Mangakyo(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.MANGAKYO, "mangakyo.vip", pageSize = 40, searchPageSize = 20) {
	override val listUrl = "/komik"
	override val datePattern = "MMM d, yyyy"
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
