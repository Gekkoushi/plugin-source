package tsuki.site.mangareader.tr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("MANGAKOLEJI", "MangaKoleji", "tr")
internal class MangaKoleji(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.MANGAKOLEJI, "mangakoleji.com", pageSize = 20, searchPageSize = 10) {
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = true,
		)
}
