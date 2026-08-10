package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("MANYTOONME", "ManyToon.me", "en", ContentType.HENTAI)
internal class ManyToonMe(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANYTOONME, "manytoon.me", 20) {
	override val listUrl = "manhwa/"
	override val tagPrefix = "manhwa-genre/"
}
