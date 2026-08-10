package tsuki.site.mangareader.en

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@Broken("Blocked by Cloudflare challenge")
@MangaSourceParser("LUMINOUSSCANS", "RadiantScans", "en")
internal class LuminousScans(context: MangaLoaderContext) :
	MangaReaderParser(
		context,
		MangaParserSource.LUMINOUSSCANS,
		"radiantscans.com",
		pageSize = 20,
		searchPageSize = 10,
	) {

	override val listUrl = "/series"
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
