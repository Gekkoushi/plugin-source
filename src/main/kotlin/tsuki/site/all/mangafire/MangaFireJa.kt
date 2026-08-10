package tsuki.site.all.mangafire

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.all.MangaFireParser

@MangaSourceParser("MANGAFIRE_JA", "MangaFire Japanese", "ja")
internal class MangaFireJa(context: MangaLoaderContext):
	MangaFireParser(context, MangaParserSource.MANGAFIRE_JA, "ja")
