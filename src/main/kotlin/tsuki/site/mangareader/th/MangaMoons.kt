package tsuki.site.mangareader.th

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@Broken("Domain hijacked — now serves a JS redirect to spam/ads")
@MangaSourceParser("MANGAMOONS", "MangaMoons", "th")
internal class MangaMoons(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.MANGAMOONS, "manga-moons.net", pageSize = 20, searchPageSize = 10) {
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
