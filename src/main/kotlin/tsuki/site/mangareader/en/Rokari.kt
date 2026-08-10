package tsuki.site.mangareader.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser
import java.util.Locale

@MangaSourceParser("ROKARICOMICS", "Rokari Comics", "en")
internal class RokariComics(context: MangaLoaderContext) :
	MangaReaderParser(
		context = context,
		source = MangaParserSource.ROKARICOMICS,
		domain = "rokaricomics.com",
		pageSize = 20,
		searchPageSize = 10,
	) {

	override val sourceLocale: Locale = Locale.ENGLISH
	override val selectChapter = "#chapterlist > ul > li:has(a[href])"

	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)

}
