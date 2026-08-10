package tsuki.site.madara.vi

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@Broken("Domain parked — landing page only, no manga content")
@MangaSourceParser("MANGAZODIAC", "MangaZodiac", "vi")
internal class MangaZodiac(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGAZODIAC, "mangazodiac.com")
