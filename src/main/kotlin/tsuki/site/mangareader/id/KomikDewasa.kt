package tsuki.site.mangareader.id

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser
import java.util.*

@MangaSourceParser("KOMIKDEWASA_ONLINE", "KomikDewasa.Online", "id", ContentType.HENTAI)
internal class KomikDewasa(context: MangaLoaderContext) :
	MangaReaderParser(
		context,
		MangaParserSource.KOMIKDEWASA_ONLINE,
		"komikdewasa.art",
		pageSize = 20,
		searchPageSize = 10,
	) {
	override val sourceLocale: Locale = Locale.ENGLISH
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
