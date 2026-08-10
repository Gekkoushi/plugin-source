package tsuki.site.mangareader.tr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("NIRVANAMANGA", "NirvanaManga", "tr")
internal class NirvanaManga(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.NIRVANAMANGA, "nirvanamanga.com", pageSize = 20, searchPageSize = 10) {
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
