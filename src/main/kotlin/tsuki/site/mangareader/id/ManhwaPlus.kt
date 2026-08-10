package tsuki.site.mangareader.id

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser
import java.util.*

@Broken("Site is online but parser is broken — layout/API changed, needs rewrite")
@MangaSourceParser("MANHWAPLUS", "ManhwaPlus", "id", ContentType.HENTAI)
internal class ManhwaPlus(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.MANHWAPLUS, "manhwablue.com", 20, 10) {
	override val sourceLocale: Locale = Locale.ENGLISH
	override val listUrl = "/komik"
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}