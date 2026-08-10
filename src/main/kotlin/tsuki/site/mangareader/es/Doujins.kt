package tsuki.site.mangareader.es

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@Broken("Domain has no DNS records — site is gone")
@MangaSourceParser("DOUJINS", "Doujins.lat", "es", ContentType.HENTAI)
internal class Doujins(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.DOUJINS, "doujins.lat", pageSize = 20, searchPageSize = 10) {
	override val listUrl = "/comic"
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
