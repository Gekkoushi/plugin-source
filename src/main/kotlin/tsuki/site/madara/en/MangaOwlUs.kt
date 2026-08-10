package tsuki.site.madara.en

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@Broken("Site is online but parser is broken — layout/API changed, needs rewrite")
@MangaSourceParser("MANGAOWL_US", "MangaOwlYaoi", "en", ContentType.HENTAI)
internal class MangaOwlUs(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGAOWL_US, "mangaowlyaoi.com")
