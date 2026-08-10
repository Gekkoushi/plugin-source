package tsuki.site.madara.es

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("VERMANHWA", "Vermanhwa", "es", ContentType.HENTAI)
internal class Vermanhwa(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.VERMANHWA, "vermanhwa.com", 10)
