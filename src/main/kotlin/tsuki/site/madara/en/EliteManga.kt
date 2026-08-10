package tsuki.site.madara.en

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@Broken("Domain has no DNS records — site is gone")
@MangaSourceParser("ELITEMANGA", "EliteManga", "en")
internal class EliteManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.ELITEMANGA, "www.beinmanga.com")
