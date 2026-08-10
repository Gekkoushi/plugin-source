package tsuki.site.hotcomics.de

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.hotcomics.HotComicsParser
import java.util.Locale

@Broken("Domain has no DNS records — site is gone")
@MangaSourceParser("TOOMICS", "Toomics", "de")
internal class Toomics(context: MangaLoaderContext) :
	HotComicsParser(context, MangaParserSource.TOOMICS, "toomics.top/de") {
	override val sourceLocale: Locale = Locale.ENGLISH
	override val isSearchSupported = false
}
