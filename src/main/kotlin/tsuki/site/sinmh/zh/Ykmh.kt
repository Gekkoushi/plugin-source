package tsuki.site.sinmh.zh

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.sinmh.SinmhParser

@MangaSourceParser("YKMH", "Ykmh", "zh")
internal class Ykmh(context: MangaLoaderContext) :
	SinmhParser(context, MangaParserSource.YKMH, "www.ykmh.net")
