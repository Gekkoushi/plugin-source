package tsuki.site.onemanga.fr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.onemanga.OneMangaParser

@MangaSourceParser("MYHEROACADEMIASCAN", "MyHeroacAdemiaScan", "fr")
internal class MyHeroacAdemiaScan(context: MangaLoaderContext) :
	OneMangaParser(context, MangaParserSource.MYHEROACADEMIASCAN, "myheroacademiascan.com")
