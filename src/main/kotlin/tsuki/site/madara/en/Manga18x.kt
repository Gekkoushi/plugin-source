package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("MANGA18X", "Manga18x", "en", ContentType.HENTAI)
internal class Manga18x(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGA18X, "manga18x.net", 24)
