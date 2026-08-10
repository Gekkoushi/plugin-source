package tsuki.site.madara.vi

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("TRUYENVN", "KhoTruyen", "vi", ContentType.HENTAI)
internal class TruyenVn(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.TRUYENVN, "truyenvn.shop", 20) {
	override val listUrl = "truyen-tranh/"
	override val tagPrefix = "the-loai/"
	override val datePattern = "dd/MM/yyyy"
}
