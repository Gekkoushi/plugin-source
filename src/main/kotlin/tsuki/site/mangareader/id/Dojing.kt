package tsuki.site.mangareader.id

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("DOJING", "Dojing", "id", ContentType.HENTAI)
internal class Dojing(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.DOJING, "dojing.net", pageSize = 12, searchPageSize = 12) {
	override val datePattern = "MMM d, yyyy"
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
