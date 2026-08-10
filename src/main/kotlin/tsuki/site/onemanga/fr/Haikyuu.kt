package tsuki.site.onemanga.fr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.onemanga.OneMangaParser

@MangaSourceParser("HAIKYUU", "Haikyuu", "fr")
internal class Haikyuu(context: MangaLoaderContext) :
	OneMangaParser(context, MangaParserSource.HAIKYUU, "haikyuu.fr")
