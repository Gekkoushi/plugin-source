package tsuki.site.all.mangafire

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.all.MangaFireParser

@MangaSourceParser("MANGAFIRE_FR", "MangaFire French", "fr")
internal class MangaFireFr(context: MangaLoaderContext):
	MangaFireParser(context, MangaParserSource.MANGAFIRE_FR, "fr")
