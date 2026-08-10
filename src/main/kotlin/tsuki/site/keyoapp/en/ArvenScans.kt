package tsuki.site.keyoapp.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.keyoapp.KeyoappParser

@MangaSourceParser("ARVENSCANS", "ArvenComics", "en")
internal class ArvenScans(context: MangaLoaderContext) :
	KeyoappParser(context, MangaParserSource.ARVENSCANS, "arvencomics.com")
