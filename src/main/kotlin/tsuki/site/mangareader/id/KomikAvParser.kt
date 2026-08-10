package tsuki.site.mangareader.id

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@Broken("Site is online but parser is broken — layout/API changed, needs rewrite")
@MangaSourceParser("KOMIKAV", "KomikAv", "id")
internal class KomikAvParser(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.KOMIKAV, "komikav.net", pageSize = 20, searchPageSize = 10) {
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
