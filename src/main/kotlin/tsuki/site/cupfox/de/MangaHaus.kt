package tsuki.site.cupfox.de

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.cupfox.CupFoxParser

@MangaSourceParser("MANGAHAUS", "MangaHaus", "de")
internal class MangaHaus(context: MangaLoaderContext) :
	CupFoxParser(context, MangaParserSource.MANGAHAUS, "www.mangahaus.com")
