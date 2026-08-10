package tsuki.site.mangareader.id

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@Broken("Domain parked — landing page only, no manga content")
@MangaSourceParser("SHEAKOMIK", "SheaKomik", "id")
internal class Sheakomik(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.SHEAKOMIK, "sheakomik.com", pageSize = 40, searchPageSize = 40) {
	override val datePattern = "MMM d, yyyy"
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
