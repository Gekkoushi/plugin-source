package tsuki.site.zeistmanga.id

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.site.zeistmanga.ZeistMangaParser

@MangaSourceParser("ARLAS", "Arlas", "id")
internal class Arlas(context: MangaLoaderContext) :
    ZeistMangaParser(context, tsuki.model.MangaParserSource.ARLAS, "arlas.my.id")
