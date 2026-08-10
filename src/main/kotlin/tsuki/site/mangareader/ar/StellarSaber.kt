package tsuki.site.mangareader.ar

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("STELLARSABER", "StellarSaber", "ar")
internal class StellarSaber(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.STELLARSABER, "stellarsaber.pro", pageSize = 32, searchPageSize = 10)
