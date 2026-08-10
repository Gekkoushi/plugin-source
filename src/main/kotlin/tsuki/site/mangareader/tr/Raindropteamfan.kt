package tsuki.site.mangareader.tr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("RAINDROPTEAMFAN", "Raindrop Fansub", "tr")
internal class Raindropteamfan(context: MangaLoaderContext) :
	MangaReaderParser(
		context,
		MangaParserSource.RAINDROPTEAMFAN,
		"www.raindropteamfan.com",
		pageSize = 25,
		searchPageSize = 10,
	) {
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}

