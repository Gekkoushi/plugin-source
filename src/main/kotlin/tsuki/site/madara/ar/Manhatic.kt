package tsuki.site.madara.ar

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser
import java.util.*

@MangaSourceParser("MANHATIC", "Manhatic", "ar", ContentType.HENTAI)
internal class Manhatic(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANHATIC, "manhatic.com") {
	override val sourceLocale: Locale = Locale.ENGLISH
}
