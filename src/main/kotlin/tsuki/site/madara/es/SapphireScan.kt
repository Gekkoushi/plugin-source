package tsuki.site.madara.es

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("SAPPHIRESCAN", "SapphireScan", "es")
internal class SapphireScan(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.SAPPHIRESCAN, "sapphirescan.com")
