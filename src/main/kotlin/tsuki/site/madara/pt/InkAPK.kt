package tsuki.site.madara.pt

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("INKAPK", "InkAPK", "pt", ContentType.HENTAI)
internal class InkAPK(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.INKAPK, "inkapk.net") {
	override val listUrl = "obras/"
	override val tagPrefix = "obras-genre/"
}
