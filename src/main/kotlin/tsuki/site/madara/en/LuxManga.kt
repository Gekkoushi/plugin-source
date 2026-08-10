package tsuki.site.madara.en

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@Broken("Domain has no DNS records — site is gone")
@MangaSourceParser("LUXMANGA", "LuxManga", "en")
internal class LuxManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.LUXMANGA, "luxmanga.net")
