package tsuki.site.madara.ar

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("ARABTOONS", "ArabToons", "ar", ContentType.HENTAI)
internal class ArabToons(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.ARABTOONS, "arabtoons.net") {
	override val datePattern = "dd-MM-yyyy"
}
