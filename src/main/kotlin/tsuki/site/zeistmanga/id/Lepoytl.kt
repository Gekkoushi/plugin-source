package tsuki.site.zeistmanga.id

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.zeistmanga.ZeistMangaParser

@MangaSourceParser("LEPOYTL", "Lepoytl", "id")
internal class Lepoytl(context: MangaLoaderContext) :
	ZeistMangaParser(context, MangaParserSource.LEPOYTL, "www.lepoytl.cloud")
