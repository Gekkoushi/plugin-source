package tsuki.site.madara.id

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser
import java.util.Locale

@MangaSourceParser("XMANHWA", "XManhwa", "id", ContentType.HENTAI)
internal class XManhwa(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.XMANHWA, "www.manhwaden.com", 10) {
	override val sourceLocale: Locale = Locale.ENGLISH
	override val selectPage = "img"
}
