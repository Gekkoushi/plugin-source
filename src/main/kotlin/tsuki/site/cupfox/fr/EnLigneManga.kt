package tsuki.site.cupfox.fr

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.cupfox.CupFoxParser

@Broken("Server not responding — connection times out")
@MangaSourceParser("ENLIGNEMANGA", "EnLigneManga", "fr")
internal class EnLigneManga(context: MangaLoaderContext) :
	CupFoxParser(context, MangaParserSource.ENLIGNEMANGA, "www.enlignemanga.com")
