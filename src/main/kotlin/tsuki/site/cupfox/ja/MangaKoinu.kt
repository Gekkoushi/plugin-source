package tsuki.site.cupfox.ja

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.cupfox.CupFoxParser

@MangaSourceParser("MANGAKOINU", "MangaKoinu", "ja")
internal class MangaKoinu(context: MangaLoaderContext) :
	CupFoxParser(context, MangaParserSource.MANGAKOINU, "www.mangakoinu.com")
