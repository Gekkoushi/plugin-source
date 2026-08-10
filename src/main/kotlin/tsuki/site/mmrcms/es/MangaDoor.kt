package tsuki.site.mmrcms.es

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.mmrcms.MmrcmsParser
import java.util.*

@Broken("Origin server returns HTTP 406")
@MangaSourceParser("MANGADOOR", "MangaDoor", "es")
internal class MangaDoor(context: MangaLoaderContext) :
	MmrcmsParser(context, MangaParserSource.MANGADOOR, "mangadoor.com") {
	override val sourceLocale: Locale = Locale.ENGLISH
	override val selectState = "dt:contains(Estado)"
	override val selectAlt = "dt:contains(Otros nombres)"
	override val selectAut = "dt:contains(Autor(es))"
	override val selectTag = "dt:contains(Categorías)"
}
