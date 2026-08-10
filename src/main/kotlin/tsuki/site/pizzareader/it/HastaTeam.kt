package tsuki.site.pizzareader.it

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.pizzareader.PizzaReaderParser

@MangaSourceParser("HASTATEAM", "HastaTeamDdt", "it")
internal class HastaTeam(context: MangaLoaderContext) :
	PizzaReaderParser(context, MangaParserSource.HASTATEAM, "ddt.hastateam.com")
