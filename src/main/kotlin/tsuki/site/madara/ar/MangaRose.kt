package tsuki.site.madara.ar

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("MANGAROSE", "MangaRose", "ar")
internal class MangaRose(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGAROSE, "mangarose.net", pageSize = 20)
