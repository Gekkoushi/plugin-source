package tsuki.site.madara.en

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@Broken("Domain hijacked — now serves a JS redirect to spam/ads")
@MangaSourceParser("BOYS_LOVE", "BoysLove", "en", ContentType.HENTAI)
internal class BoysLove(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.BOYS_LOVE, "boyslove.me", 20) {
	override val tagPrefix = "boyslove-genre/"
	override val listUrl = "boyslove/"
	override val postReq = true
}
