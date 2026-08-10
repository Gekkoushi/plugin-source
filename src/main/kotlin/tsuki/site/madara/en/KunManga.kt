package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("KUNMANGA", "KunManga", "en")
internal class KunManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.KUNMANGA, "kunmanga.com", 10)
