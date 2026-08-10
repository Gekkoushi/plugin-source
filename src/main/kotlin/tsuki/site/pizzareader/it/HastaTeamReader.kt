package tsuki.site.pizzareader.it

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.pizzareader.PizzaReaderParser

@MangaSourceParser("HASTATEAM_READER", "HastaTeamReader", "it")
internal class HastaTeamReader(context: MangaLoaderContext) :
	PizzaReaderParser(context, MangaParserSource.HASTATEAM_READER, "reader.hastateam.com")
