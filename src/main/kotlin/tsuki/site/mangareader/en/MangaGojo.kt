package tsuki.site.mangareader.en

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@Broken("Domain has no DNS records — site is gone") // mangagojo.com domain is dead (NXDOMAIN); operators scattered content across iframe-redirect chains (mangagojo.my -> freeonlinek.top -> weebrook.com) with no clean one-to-one replacement.
@MangaSourceParser("MANGAGOJO", "MangaGojo", "en")
internal class MangaGojo(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.MANGAGOJO, "mangagojo.com", 30, 20)
