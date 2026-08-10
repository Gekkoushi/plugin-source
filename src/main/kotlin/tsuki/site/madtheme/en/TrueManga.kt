package tsuki.site.madtheme.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madtheme.MadthemeParser

@MangaSourceParser("TRUEMANGA", "TrueManga", "en")
internal class TrueManga(context: MangaLoaderContext) :
	MadthemeParser(context, MangaParserSource.TRUEMANGA, "mangamonk.com")
