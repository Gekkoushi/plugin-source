package tsuki.site.liliana.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.liliana.LilianaParser

@MangaSourceParser("MANHUAGOLD", "ManhuaGold", "en")
internal class ManhuaGold(context: MangaLoaderContext) :
	LilianaParser(context, MangaParserSource.MANHUAGOLD, "manhuagold.top")
