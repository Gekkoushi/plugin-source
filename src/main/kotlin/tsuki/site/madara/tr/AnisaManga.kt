package tsuki.site.madara.tr

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@Broken("Domain has no DNS records — site is gone")
@MangaSourceParser("ANISA_MANGA", "AnisaManga", "tr")
internal class AnisaManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.ANISA_MANGA, "anisamanga.com")
