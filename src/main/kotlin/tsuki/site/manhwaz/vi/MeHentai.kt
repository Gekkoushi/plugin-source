package tsuki.site.manhwaz.vi

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.manhwaz.ManhwaZ

@MangaSourceParser("MEHENTAI", "MeHentai", "vi", ContentType.HENTAI)
internal class MeHentai(context: MangaLoaderContext) :
	ManhwaZ(context, MangaParserSource.MEHENTAI, "mehentai.tv") {

	override val searchPath = "tim-kiem"
	override val tagPath = "the-loai"
}
