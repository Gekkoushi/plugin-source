package tsuki.site.sinmh.zh

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.sinmh.SinmhParser
import tsuki.Broken

@Broken("Server not responding — connection times out")
@MangaSourceParser("GUFENGMH", "Gufengmh", "zh")
internal class Gufengmh(context: MangaLoaderContext) :
	SinmhParser(context, MangaParserSource.GUFENGMH, "www.gufengmh.com")
