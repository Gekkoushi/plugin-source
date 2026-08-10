package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("SETSUSCANS", "SetsuScans", "en")
internal class SetsuScans(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.SETSUSCANS, "setsuscans.com")
