package tsuki.site.mangareader.pl

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("SKANLACJEFENIKSY", "SkanlacjeFeniksy", "pl")
internal class SkanlacjeFeniksy(context: MangaLoaderContext) :
	MangaReaderParser(
		context,
		MangaParserSource.SKANLACJEFENIKSY,
		"skanlacje-feniksy.pl",
		pageSize = 10,
		searchPageSize = 10,
	) {
	override val datePattern = "d MMMM, yyyy"
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
