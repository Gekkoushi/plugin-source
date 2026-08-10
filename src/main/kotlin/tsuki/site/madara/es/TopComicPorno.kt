package tsuki.site.madara.es

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("TOPCOMICPORNO", "TopComicPorno", "es", ContentType.HENTAI)
internal class TopComicPorno(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.TOPCOMICPORNO, "topcomicporno.net", 18) {
	override val datePattern = "MMM dd, yy"
}
