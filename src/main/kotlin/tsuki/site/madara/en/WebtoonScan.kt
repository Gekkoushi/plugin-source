package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("WEBTOONSCAN", "WebtoonScan", "en", ContentType.HENTAI)
internal class WebtoonScan(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.WEBTOONSCAN, "webtoonscan.com", 20)
