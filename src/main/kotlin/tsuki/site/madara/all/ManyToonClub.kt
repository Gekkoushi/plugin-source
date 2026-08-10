package tsuki.site.madara.all

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser
import java.util.*

@MangaSourceParser("MANYTOON_CLUB", "ManyToon.club", "", ContentType.HENTAI)
internal class ManyToonClub(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANYTOON_CLUB, "manytoon.club") {
	override val postReq = true
	override val listUrl = "manhwa-raw/"
	override val tagPrefix = "manhwa-raw-genre/"
	override val sourceLocale: Locale = Locale.ENGLISH
}
