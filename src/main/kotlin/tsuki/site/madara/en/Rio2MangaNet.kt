package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("RIO2MANGANET", "ZinchanManga.mobi", "en")
internal class Rio2MangaNet(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.RIO2MANGANET, "zinchanmanga.mobi", 10)
