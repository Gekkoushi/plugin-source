package tsuki.site.madara.tr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("LILYUMFANSUB", "LilyumFansub", "tr")
internal class LilyumFansub(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.LILYUMFANSUB, "lilyumfansub.com.tr", 16)
