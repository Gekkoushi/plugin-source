package tsuki.site.zeistmanga.pt

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.zeistmanga.ZeistMangaParser

@MangaSourceParser("HECKSCANS", "HeckScans", "pt")
internal class HeckScans(context: MangaLoaderContext) :
	ZeistMangaParser(context, MangaParserSource.HECKSCANS, "heckscans.blogspot.com")
