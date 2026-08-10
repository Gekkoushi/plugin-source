package tsuki.site.keyoapp.fr

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.keyoapp.KeyoappParser

@Broken("Domain hijacked — now serves a JS redirect to spam/ads")
@MangaSourceParser("STARBOUNDSCANS", "StarboundScans", "fr")
internal class StarboundScans(context: MangaLoaderContext) :
	KeyoappParser(context, MangaParserSource.STARBOUNDSCANS, "starboundscans.com")
