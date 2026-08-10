package tsuki.site.zeistmanga.id

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.zeistmanga.ZeistMangaParser

@MangaSourceParser("ICHIROMANGA", "IchiroManga", "id")
internal class IchiroManga(context: MangaLoaderContext) :
	ZeistMangaParser(context, MangaParserSource.ICHIROMANGA, "ichiromanga.my.id")
