package tsuki.site.madara.vi

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("TRUYENTRANHFULL", "Truyện Tranh Full", "vi")
internal class TruyenTranhFull(context: MangaLoaderContext) :
    MadaraParser(context, MangaParserSource.TRUYENTRANHFULL, "truyentranhfull.net", 20) {
    override val listUrl = "truyen-tranh/"
    override val tagPrefix = "the-loai/"
    override val datePattern = "dd/MM/yyyy"
}
