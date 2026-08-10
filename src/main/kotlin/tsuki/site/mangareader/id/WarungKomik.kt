package tsuki.site.mangareader.id

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@Broken("Blocked by Cloudflare challenge")
@MangaSourceParser("WARUNGKOMIK", "WarungKomik", "id")
internal class WarungKomik(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.WARUNGKOMIK, "warungkomik.com", pageSize = 20, searchPageSize = 10) {
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}