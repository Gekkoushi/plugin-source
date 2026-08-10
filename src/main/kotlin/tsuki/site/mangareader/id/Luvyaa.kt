package tsuki.site.mangareader.id

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("LUVYAA", "Luvyaa", "id", ContentType.HENTAI)
internal class Luvyaa(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.LUVYAA, "v2.luvyaa.co", 20, 10) {
	override val datePattern = "MMM d, yyyy"
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
