package tsuki.site.onemanga.fr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.onemanga.OneMangaParser

@MangaSourceParser("OSHINOKO", "OshiNoKo", "fr")
internal class OshiNoKo(context: MangaLoaderContext) :
	OneMangaParser(context, MangaParserSource.OSHINOKO, "oshinoko.fr")
