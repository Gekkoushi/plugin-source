package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser
import tsuki.Broken

@Broken("Site is gone — root redirects to an unrelated domain")
@MangaSourceParser("FREECOMICONLINE", "FreeComicOnline", "en", ContentType.HENTAI)
internal class FreeComicOnline(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.FREECOMICONLINE, "freecomiconline.me") {
	override val postReq = true
	override val listUrl = "comic/"
	override val tagPrefix = "comic-genre/"
}
