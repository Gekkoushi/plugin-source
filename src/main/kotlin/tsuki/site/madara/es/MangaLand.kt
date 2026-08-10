package tsuki.site.madara.es

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("MANGALAND", "MangaLand", "es", ContentType.HENTAI)
internal class MangaLand(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGALAND, "mangaland.net")
