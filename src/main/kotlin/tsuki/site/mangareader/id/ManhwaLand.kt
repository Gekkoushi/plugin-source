package tsuki.site.mangareader.id

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("MANHWALAND", "ManhwaLand.vip", "id", ContentType.HENTAI)
internal class ManhwaLand(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.MANHWALAND, "www.manhwaland.baby", pageSize = 20, searchPageSize = 10) {
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
	override val datePattern = "MMM d, yyyy"
}
