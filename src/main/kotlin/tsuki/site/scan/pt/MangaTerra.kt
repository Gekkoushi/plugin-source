package tsuki.site.scan.pt

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.scan.ScanParser

@MangaSourceParser("MANGATERRA", "MangaTerra", "pt")
internal class MangaTerra(context: MangaLoaderContext) :
	ScanParser(context, MangaParserSource.MANGATERRA, "manga-terra.com")
