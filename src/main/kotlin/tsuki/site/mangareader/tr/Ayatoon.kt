package tsuki.site.mangareader.tr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("AYATOON", "AyaToon", "tr")
internal class Ayatoon(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.AYATOON, "ayatoon.com", pageSize = 20, searchPageSize = 20) {

	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
