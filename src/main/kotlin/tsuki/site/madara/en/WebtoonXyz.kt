package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("WEBTOONXYZ", "Webtoon.xyz", "en", ContentType.HENTAI)
internal class WebtoonXyz(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.WEBTOONXYZ, "www.webtoon.xyz", 20) {
	override val tagPrefix = "webtoon-genre/"
	override val listUrl = "read/"
	override val datePattern = "d MMM yyyy"
}
