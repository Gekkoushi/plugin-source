package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("MANHWACLUB", "Manhwaclub", "en", ContentType.HENTAI)
internal class ManhwaClub(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANHWACLUB, "manhwaclub.net") {
	override val postReq = true
}
