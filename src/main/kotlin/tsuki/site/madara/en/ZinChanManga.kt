package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("ZINCHANMANGA", "ZinChanManga.Com", "en", ContentType.HENTAI)
internal class ZinChanManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.ZINCHANMANGA, "zinchangmanga.net", 10)
