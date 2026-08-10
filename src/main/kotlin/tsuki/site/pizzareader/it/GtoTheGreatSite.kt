package tsuki.site.pizzareader.it

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.pizzareader.PizzaReaderParser

@MangaSourceParser("GTOTHEGREATSITE", "GtoTheGreatSite", "it")
internal class GtoTheGreatSite(context: MangaLoaderContext) :
	PizzaReaderParser(context, MangaParserSource.GTOTHEGREATSITE, "reader.gtothegreatsite.net")
