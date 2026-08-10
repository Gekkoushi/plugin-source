package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("RESETSCANS", "Resetscans", "en")
internal class ResetScans(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.RESETSCANS, "reset-scans.org", 20)

