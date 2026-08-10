package tsuki.site.madara.en

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@Broken("Domain has no DNS records — site is gone")
@MangaSourceParser("ZINMANGA_CC", "ZinManga.cc", "en")
internal class ZinMangaCc(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.ZINMANGA_CC, "zinmanga.cc")
