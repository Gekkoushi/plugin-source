package tsuki.site.madara.tr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("MANGAWT", "MangaWt.com", "tr")
internal class Mangawt(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGAWT, "mangawt.com")
