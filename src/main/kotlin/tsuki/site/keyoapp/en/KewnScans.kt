package tsuki.site.keyoapp.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.keyoapp.KeyoappParser

@MangaSourceParser("KEWNSCANS", "KewnScans", "en")
internal class KewnScans(context: MangaLoaderContext) :
	KeyoappParser(context, MangaParserSource.KEWNSCANS, "kewnscans.org")
