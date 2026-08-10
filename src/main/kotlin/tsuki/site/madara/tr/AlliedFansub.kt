package tsuki.site.madara.tr

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@Broken("Domain hijacked — now serves a JS redirect to spam/ads")
@MangaSourceParser("ALLIED_FANSUB", "AlliedFansub", "tr", ContentType.HENTAI)
internal class AlliedFansub(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.ALLIED_FANSUB, "alliedfansub.net", 20) {
	override val datePattern = "dd/MM/yyyy"
}