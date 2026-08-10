package tsuki.site.manga18.en

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.manga18.Manga18Parser

@Broken("Domain hijacked — now serves a JS redirect to spam/ads")
@MangaSourceParser("COMIC1000", "Comic1000", "en")
internal class Comic1000(context: MangaLoaderContext) :
	Manga18Parser(context, MangaParserSource.COMIC1000, "comic1000.com")
