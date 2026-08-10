package tsuki.site.liliana.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.liliana.LilianaParser

@MangaSourceParser("MANGASECT", "MangaSect", "en")
internal class MangaSect(context: MangaLoaderContext) :
	LilianaParser(context, MangaParserSource.MANGASECT, "mangasect.net")
