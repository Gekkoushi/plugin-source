package tsuki.site.iken.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.iken.IkenParser

@MangaSourceParser("VORTEXSCANS", "VortexScans", "en")
internal class VortexScans(context: MangaLoaderContext) :
	IkenParser(context, MangaParserSource.VORTEXSCANS, "vortexscans.org", 18, true)
