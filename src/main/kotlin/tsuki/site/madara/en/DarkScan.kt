package tsuki.site.madara.en

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@Broken("Domain hijacked — now serves a JS redirect to spam/ads")
@MangaSourceParser("DARKSCAN", "Dark-Scan", "en")
internal class DarkScan(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.DARKSCAN, "dark-scan.com")
