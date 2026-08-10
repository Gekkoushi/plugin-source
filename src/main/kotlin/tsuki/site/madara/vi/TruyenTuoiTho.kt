package tsuki.site.madara.vi

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("TRUYENTUOITHO", "Truyện Tuổi Thơ", "vi")
internal class TruyenTuoiTho(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.TRUYENTUOITHO, "truyentuoitho.com") {
	override val datePattern = "dd/MM/yyyy"
	override val withoutAjax = true
}
