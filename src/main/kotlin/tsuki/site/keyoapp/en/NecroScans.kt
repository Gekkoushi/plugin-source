package tsuki.site.keyoapp.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.keyoapp.KeyoappParser

@MangaSourceParser("NECROSCANS", "NecroScans", "en")
internal class NecroScans(context: MangaLoaderContext) :
	KeyoappParser(context, MangaParserSource.NECROSCANS, "necroscans.com")
