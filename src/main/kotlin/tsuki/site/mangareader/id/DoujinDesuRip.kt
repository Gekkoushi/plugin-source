package tsuki.site.mangareader.id

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("DOUJINDESURIP", "DoujinDesu.click", "id", ContentType.HENTAI)
internal class DoujinDesuRip(context: MangaLoaderContext) :
	MangaReaderParser(
		context,
		MangaParserSource.DOUJINDESURIP,
		"doujindesu.asia",
		pageSize = 20,
		searchPageSize = 10,
	) {

	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
