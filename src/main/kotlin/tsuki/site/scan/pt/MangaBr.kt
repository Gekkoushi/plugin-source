package tsuki.site.scan.pt

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.scan.ScanParser

@MangaSourceParser("MANGABR", "MangaBr", "pt")
internal class MangaBr(context: MangaLoaderContext) :
	ScanParser(context, MangaParserSource.MANGABR, "mangabr.net")
