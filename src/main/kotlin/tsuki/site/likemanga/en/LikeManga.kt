package tsuki.site.likemanga.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.likemanga.LikeMangaParser

@MangaSourceParser("LIKEMANGA", "LikeManga", "en")
internal class LikeManga(context: MangaLoaderContext) :
	LikeMangaParser(context, MangaParserSource.LIKEMANGA, "likemanga.ink")
