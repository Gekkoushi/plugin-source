package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("MANGATX_GG", "MangaTx.gg", "en")
internal class MangaTxGg(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGATX_GG, "mangatx.gg")
