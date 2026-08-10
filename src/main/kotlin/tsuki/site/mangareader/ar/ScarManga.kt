package tsuki.site.mangareader.ar

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@Broken("Domain hijacked — now serves a JS redirect to spam/ads")
@MangaSourceParser("SCARMANGA", "ScarManga", "ar")
internal class ScarManga(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.SCARMANGA, "scarmanga.com", pageSize = 20, searchPageSize = 10) {
	override val listUrl = "/series"
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
