package tsuki.site.all.mangafire

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.all.MangaFireParser

@MangaSourceParser("MANGAFIRE_PT", "MangaFire Portuguese", "pt")
internal class MangaFirePt(context: MangaLoaderContext):
	MangaFireParser(context, MangaParserSource.MANGAFIRE_PT, "pt")
