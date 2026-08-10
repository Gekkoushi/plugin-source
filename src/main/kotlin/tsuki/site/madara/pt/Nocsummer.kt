package tsuki.site.madara.pt

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("NOCSUMMER", "NocturneSummer", "pt", ContentType.HENTAI)
internal class Nocsummer(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.NOCSUMMER, "nocfsb.com", 18) {
	override val datePattern = "dd 'de' MMMMM 'de' yyyy"
}
