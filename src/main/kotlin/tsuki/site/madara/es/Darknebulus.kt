package tsuki.site.madara.es

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser
import tsuki.Broken

@Broken("Original site closed")
@MangaSourceParser("DARKNEBULUS", "Darknebulus", "es")
internal class Darknebulus(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.DARKNEBULUS, "www.darknebulus.com")
