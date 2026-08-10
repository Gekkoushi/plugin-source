package tsuki.site.all.mangafire

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.all.MangaFireParser

@MangaSourceParser("MANGAFIRE_ES", "MangaFire Spanish", "es")
internal class MangaFireEs(context: MangaLoaderContext):
	MangaFireParser(context, MangaParserSource.MANGAFIRE_ES, "es")
