package tsuki.site.mangareader.tr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("ARCURAFANSUB", "ArcuraFansub", "tr", ContentType.HENTAI)
internal class ArcuraFansub(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.ARCURAFANSUB, "arcurafansub.com", pageSize = 20, searchPageSize = 10) {
	override val listUrl = "/seri"

	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
