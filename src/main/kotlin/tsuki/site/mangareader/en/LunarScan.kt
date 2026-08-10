package tsuki.site.mangareader.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("LUNAR_SCAN", "LunarScan.org", "en", ContentType.HENTAI)
internal class LunarScan(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.LUNAR_SCAN, "lunarscan.org", pageSize = 20, searchPageSize = 20) {
	override val listUrl = "/series"
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
