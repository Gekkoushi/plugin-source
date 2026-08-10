package tsuki.site.mangaworld.it

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.mangaworld.MangaWorldParser

@MangaSourceParser("MANGAWORLDADULT", "MangaWorldAdult", "it")
internal class MangaWorldAdult(
	context: MangaLoaderContext,
) : MangaWorldParser(context, MangaParserSource.MANGAWORLDADULT, "mangaworldadult.net")
