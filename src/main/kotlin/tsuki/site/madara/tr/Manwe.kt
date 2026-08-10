package tsuki.site.madara.tr

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@Broken("Domain parked — landing page only, no manga content")
@MangaSourceParser("MANWE", "Manwe", "tr")
internal class Manwe(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANWE, "manwe.pro", 20)
