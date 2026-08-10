package tsuki.site.madara.en

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@Broken("Domain has no DNS records — site is gone")
@MangaSourceParser("ZINMANGA_MS", "ZinManga.ms", "en")
internal class ZinMangaMS(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.ZINMANGA_MS, "zinmanga.ms") {
	override val listUrl = "manga-1/"
	override val tagPrefix = "manga-genre-1/"
}
