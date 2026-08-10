package tsuki.site.mangaworld.it

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.mangaworld.MangaWorldParser

@MangaSourceParser("MANGAWORLD", "MangaWorld", "it")
internal class MangaWorld(
	context: MangaLoaderContext,
) : MangaWorldParser(context, MangaParserSource.MANGAWORLD, "mangaworld.ac")
