package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("ARYASCANS", "AryaScans", "en")
internal class AryaScans(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.ARYASCANS, "aryascans.com")
