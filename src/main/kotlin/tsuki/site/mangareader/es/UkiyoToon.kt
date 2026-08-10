package tsuki.site.mangareader.es

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser
import tsuki.Broken

@Broken("Domain has no DNS records — site is gone")
@MangaSourceParser("UKIYOTOON", "UkiyoToon", "es")
internal class UkiyoToon(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.UKIYOTOON, "nakamatoon.com", 30, 10) {
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
