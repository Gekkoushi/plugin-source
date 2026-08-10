package tsuki.site.mangareader.tr

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@Broken("Server not responding — connection times out")
@MangaSourceParser("TEMPESTFANSUB", "TempestFansub.Com", "tr")
internal class TempestfansubParser(context: MangaLoaderContext) :
	MangaReaderParser(
		context,
		MangaParserSource.TEMPESTFANSUB,
		"tempestscans.net",
		pageSize = 25,
		searchPageSize = 40,
	) {
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
