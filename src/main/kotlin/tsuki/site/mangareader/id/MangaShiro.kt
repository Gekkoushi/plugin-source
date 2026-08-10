package tsuki.site.mangareader.id

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser
import java.util.*

@Broken("Domain parked — landing page only, no manga content")
@MangaSourceParser("MANGASHIRO", "MangaShiro", "id")
internal class MangaShiro(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.MANGASHIRO, "mangashiro.me", pageSize = 20, searchPageSize = 10) {
	override val sourceLocale: Locale = Locale.ENGLISH
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}