package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("ALLPORN_COMIC", "AllPornComic", "en", ContentType.HENTAI)
internal class AllPornComic(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.ALLPORN_COMIC, "allporncomic.com", pageSize = 24) {
	override val tagPrefix = "porncomic-genre/"
	override val datePattern = "MMMM dd, yyyy"
}
