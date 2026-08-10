package tsuki.site.madara.pt

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("HIKARISCAN", "HikariScan", "pt")
internal class HikariScan(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.HIKARISCAN, "hikariscan.org")
