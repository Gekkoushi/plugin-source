package tsuki.site.onemanga.fr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.onemanga.OneMangaParser

@MangaSourceParser("ONEPUNCHMANSCAN", "OnePunchManScan", "fr")
internal class OnePunchManScan(context: MangaLoaderContext) :
	OneMangaParser(context, MangaParserSource.ONEPUNCHMANSCAN, "onepunchmanscan.com")
