package tsuki.site.madara.pt

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("ARTHUR_SCAN", "ArthurScan", "pt")
internal class ArthurScan(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.ARTHUR_SCAN, "arthurscan.xyz")
