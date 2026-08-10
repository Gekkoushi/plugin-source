package tsuki.site.mangareader.en

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@Broken("Domain parked — landing page only, no manga content")
@MangaSourceParser("ANIGLISCANS", "AnigliScans", "en")
internal class AnigliScans(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.ANIGLISCANS, "anigliscans.xyz", pageSize = 47, searchPageSize = 47) {
	override val listUrl = "/series"

	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
