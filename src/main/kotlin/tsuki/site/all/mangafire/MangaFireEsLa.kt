package tsuki.site.all.mangafire

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.all.MangaFireParser

@MangaSourceParser("MANGAFIRE_ESLA", "MangaFire Spanish (Latim)", "es")
internal class MangaFireEsLa(context: MangaLoaderContext):
	MangaFireParser(context, MangaParserSource.MANGAFIRE_ESLA, "es-la")
