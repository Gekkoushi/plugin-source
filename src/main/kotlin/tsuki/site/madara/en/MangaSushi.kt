package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("MANGASUSHI", "MangaSushi", "en")
internal class MangaSushi(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGASUSHI, "mangasushi.org")
