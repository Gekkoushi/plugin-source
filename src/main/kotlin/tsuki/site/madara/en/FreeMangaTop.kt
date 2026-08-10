package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.network.CommonHeaders
import tsuki.site.madara.MadaraParser

@MangaSourceParser("FREEMANGATOP", "FreeMangaTop", "en")
internal class FreeMangaTop(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.FREEMANGATOP, "freemangatop.com") {
	override val datePattern = "MM/dd/yyyy"

    override fun getRequestHeaders() = super.getRequestHeaders().newBuilder()
        .add(CommonHeaders.REFERER, "https://$domain/")
        .build()
}
