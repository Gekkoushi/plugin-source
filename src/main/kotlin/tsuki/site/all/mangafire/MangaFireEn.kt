package tsuki.site.all.mangafire

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.all.MangaFireParser

@MangaSourceParser("MANGAFIRE_EN", "MangaFire English", "en")
internal class MangaFireEn(context: MangaLoaderContext):
	MangaFireParser(context, MangaParserSource.MANGAFIRE_EN, "en")
