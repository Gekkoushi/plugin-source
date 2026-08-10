package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser
import tsuki.Broken

@Broken("Site is gone — root redirects to an unrelated domain")
@MangaSourceParser("MANYCOMIC", "ManyComic", "en", ContentType.HENTAI)
internal class ManyComic(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANYCOMIC, "manycomic.com") {
	override val postReq = true
	override val tagPrefix = "comic-genre/"
}
