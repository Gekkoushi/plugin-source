package tsuki.site.zeistmanga.ar

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.zeistmanga.ZeistMangaParser

@MangaSourceParser("XSANOMANGA", "XsanoManga", "ar")
internal class XsanoManga(context: MangaLoaderContext) :
	ZeistMangaParser(context, MangaParserSource.XSANOMANGA, "www.xsano-manga.com")
