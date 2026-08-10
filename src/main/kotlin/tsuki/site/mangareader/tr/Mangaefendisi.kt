package tsuki.site.mangareader.tr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("MANGAEFENDISI", "MangaEfendisi", "tr")
internal class Mangaefendisi(context: MangaLoaderContext) :
	MangaReaderParser(
		context,
		MangaParserSource.MANGAEFENDISI,
		"mangaefendisi.net",
		pageSize = 30,
		searchPageSize = 20,
	) {
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
