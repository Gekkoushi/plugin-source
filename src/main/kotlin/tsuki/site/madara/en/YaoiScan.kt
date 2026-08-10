package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("YAOISCAN", "YaoiScan", "en", ContentType.HENTAI)
internal class YaoiScan(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.YAOISCAN, "yaoiscan.com", 20)
