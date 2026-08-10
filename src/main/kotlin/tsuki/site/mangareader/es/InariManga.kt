package tsuki.site.mangareader.es

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser
import tsuki.Broken

@Broken("Site is online but parser is broken — layout/API changed, needs rewrite")
@MangaSourceParser("INARIMANGA", "InariManga", "es")
internal class InariManga(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.INARIMANGA, "clubinari.org", pageSize = 20, searchPageSize = 10) {
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
