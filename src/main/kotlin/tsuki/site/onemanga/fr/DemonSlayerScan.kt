package tsuki.site.onemanga.fr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.onemanga.OneMangaParser

@MangaSourceParser("DEMONSLAYERSCAN", "DemonSlayerScan", "fr")
internal class DemonSlayerScan(context: MangaLoaderContext) :
	OneMangaParser(context, MangaParserSource.DEMONSLAYERSCAN, "demonslayerscan.com")
