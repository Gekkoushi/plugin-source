package tsuki.site.madara.th

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentRating
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaListFilterOptions
import tsuki.model.MangaParserSource
import tsuki.model.MangaTag
import tsuki.site.madara.MadaraParser
import java.util.EnumSet
import java.util.Locale

@MangaSourceParser("MANHUAKEY", "ManhuaKey", "th")
internal class Manhuakey(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANHUAKEY, "www.manhuakey.com", 10) {

	override val datePattern: String = "d MMMM yyyy"
	override val sourceLocale: Locale = Locale.ENGLISH
	override val withoutAjax = true
	override val selectPage = "div.text-center"

	override val filterCapabilities: MangaListFilterCapabilities
		get() = MangaListFilterCapabilities(
			isSearchSupported = true,
		)

	override suspend fun getFilterOptions() = MangaListFilterOptions(
		availableContentRating = EnumSet.of(ContentRating.SAFE, ContentRating.ADULT),
	)

	override suspend fun fetchAvailableTags(): Set<MangaTag> = emptySet()
}
