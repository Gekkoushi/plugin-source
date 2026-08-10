package tsuki.site.mangareader.id

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser
import java.util.Locale

@MangaSourceParser("MANHWALIST_ORG", "ManhwaList.org", "id")
internal class ManhwaListOrg(context: MangaLoaderContext) :
	MangaReaderParser(
		context,
		MangaParserSource.MANHWALIST_ORG,
		"isekaikomik.com",
		pageSize = 30,
		searchPageSize = 10,
	) {
	override val sourceLocale: Locale = Locale.ENGLISH
	override val listUrl = "/manga"
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
