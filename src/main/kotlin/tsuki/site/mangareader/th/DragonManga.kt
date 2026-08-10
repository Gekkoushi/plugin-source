package tsuki.site.mangareader.th

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("DRAGONMANGA", "DragonManga", "th", ContentType.HENTAI)
internal class DragonManga(context: MangaLoaderContext) :
	MangaReaderParser(
		context,
		MangaParserSource.DRAGONMANGA,
		"www.dragon-manga.com",
		pageSize = 40,
		searchPageSize = 10,
	)
