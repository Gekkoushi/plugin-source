package tsuki.site.zeistmanga.ar

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.zeistmanga.ZeistMangaParser

@MangaSourceParser("HIJALA", "Hijala", "ar")
internal class Hijala(context: MangaLoaderContext) :
	ZeistMangaParser(context, MangaParserSource.HIJALA, "hijala.com")
