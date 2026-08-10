package tsuki.site.scan.it

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.scan.ScanParser

@MangaSourceParser("MANGAITALIA", "MangaItalia", "it")
internal class MangaItalia(context: MangaLoaderContext) :
	ScanParser(context, MangaParserSource.MANGAITALIA, "mangaita.io")
