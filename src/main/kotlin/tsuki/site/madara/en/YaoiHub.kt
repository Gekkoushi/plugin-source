package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("YAOIHUB", "YaoiHub", "en", ContentType.HENTAI)
internal class YaoiHub(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.YAOIHUB, "yaoihub.com")
