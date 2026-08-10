package tsuki.site.liliana.ja

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.liliana.LilianaParser

@MangaSourceParser("MANGAKOMA01", "MangaKoma01", "ja")
internal class MangaKoma01(context: MangaLoaderContext) :
	LilianaParser(context, MangaParserSource.MANGAKOMA01, "mangakoma01.com")
