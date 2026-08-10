package tsuki.site.madara.ar

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("MANGALIONZ", "Manga-Lionz", "ar")
internal class MangaLionz(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGALIONZ, "manga-lionz.com", pageSize = 10)
