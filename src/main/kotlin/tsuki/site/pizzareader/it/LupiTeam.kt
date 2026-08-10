package tsuki.site.pizzareader.it

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.pizzareader.PizzaReaderParser

@MangaSourceParser("LUPITEAM", "LupiTeam", "it")
internal class LupiTeam(context: MangaLoaderContext) :
	PizzaReaderParser(context, MangaParserSource.LUPITEAM, "lupiteam.net")
