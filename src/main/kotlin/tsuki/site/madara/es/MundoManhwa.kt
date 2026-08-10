package tsuki.site.madara.es

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("MUNDO_MANHWA", "MundoManhwa", "es")
internal class MundoManhwa(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MUNDO_MANHWA, "mundomanhwa.com", 10)
