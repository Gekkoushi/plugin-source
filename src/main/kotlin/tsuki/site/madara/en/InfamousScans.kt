package tsuki.site.madara.en

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@Broken("Domain parked — landing page only, no manga content")
@MangaSourceParser("INFAMOUSSCANS", "InfamousScans", "en")
internal class InfamousScans(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.INFAMOUSSCANS, "infamous-scans.com", 10)
