package tsuki.site.manga18.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.manga18.Manga18Parser

@MangaSourceParser("MANGA18", "Manga18", "en", ContentType.HENTAI)
internal class Manga18(context: MangaLoaderContext) :
	Manga18Parser(context, MangaParserSource.MANGA18, "manga18.club")
