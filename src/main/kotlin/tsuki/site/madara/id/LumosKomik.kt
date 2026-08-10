package tsuki.site.madara.id

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser
import java.util.*

@MangaSourceParser("LUMOSKOMIK", "LumosKomik", "id")
internal class LumosKomik(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.LUMOSKOMIK, "lumos01.com") {
	override val tagPrefix = "genre/"
	override val listUrl = "komik/"
	override val datePattern = "dd MMMM yyyy"
	override val sourceLocale: Locale = Locale.ENGLISH
}
