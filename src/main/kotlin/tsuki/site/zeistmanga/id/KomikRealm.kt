package tsuki.site.zeistmanga.id

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.zeistmanga.ZeistMangaParser

@MangaSourceParser("KOMIKREALM", "KomikRealm", "id")
internal class KomikRealm(context: MangaLoaderContext) :
	ZeistMangaParser(context, MangaParserSource.KOMIKREALM, "komikrealm.org")
