package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("MANHWATOON", "ManhwaToon", "en", ContentType.HENTAI)
internal class ManhwaToon(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANHWATOON, "www.manhwatoon.com")
