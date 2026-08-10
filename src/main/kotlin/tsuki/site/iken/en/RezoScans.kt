package tsuki.site.iken.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.iken.IkenParser

@MangaSourceParser("REZOSCANS", "Rezo Scans", "en")
internal class RezoScans(context: MangaLoaderContext) :
	IkenParser(context, MangaParserSource.REZOSCANS, "rezoscan.org", 18, true)
