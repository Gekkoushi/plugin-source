package tsuki.site.pizzareader.it

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.pizzareader.PizzaReaderParser

@MangaSourceParser("PHOENIXSCANS", "PhoenixScans", "it")
internal class PhoenixScans(context: MangaLoaderContext) :
	PizzaReaderParser(context, MangaParserSource.PHOENIXSCANS, "www.phoenixscans.com")
