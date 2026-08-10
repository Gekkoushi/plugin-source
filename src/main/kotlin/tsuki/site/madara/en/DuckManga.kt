package tsuki.site.madara.en

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@Broken("Domain hijacked — now serves a JS redirect to spam/ads")
@MangaSourceParser("DUCKMANGA", "DuckManga", "en", ContentType.HENTAI)
internal class DuckManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.DUCKMANGA, "duckmanga.com", 20)
