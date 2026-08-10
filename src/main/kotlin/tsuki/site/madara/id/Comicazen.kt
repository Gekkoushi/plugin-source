package tsuki.site.madara.id

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("COMICAZEN", "Comicazen", "id")
internal class Comicazen(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.COMICAZEN, "comicazen.com")
