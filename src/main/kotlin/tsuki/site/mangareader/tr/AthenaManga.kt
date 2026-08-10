package tsuki.site.mangareader.tr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("ATHENAMANGA", "AthenaManga", "tr")
internal class AthenaManga(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.ATHENAMANGA, "athenamanga.com", pageSize = 20, searchPageSize = 10) {

	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isMultipleTagsSupported = false,
		)
}
