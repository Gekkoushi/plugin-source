package tsuki.site.all.mangafire

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.all.MangaFireParser

@MangaSourceParser("MANGAFIRE_PTBR", "MangaFire Portuguese (Brazil)", "pt")
internal class MangaFirePtBr(context: MangaLoaderContext) :
	MangaFireParser(context, MangaParserSource.MANGAFIRE_PTBR, "pt-br")
