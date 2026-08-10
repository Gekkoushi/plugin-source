package tsuki.site.mangareader.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaListFilterOptions
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("SKY_MANGA", "SkyManga", "en", ContentType.HENTAI)
internal class SkyManga(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.SKY_MANGA, "skymanga.work", pageSize = 20, searchPageSize = 20) {
	override val listUrl = "/manga-list"
	override val datePattern = "dd-MM-yyyy"

	// Server ignores ?s=, genre[], type=, status= — only order= and page= are honoured.
	// Verified 2026-04-24 against live skymanga.work: every search query returns the same 7
	// featured titles; every genre/type/status value returns the same 30 titles as the default
	// sort. See PR body for the probe details.
	override val filterCapabilities = MangaListFilterCapabilities()

	override suspend fun getFilterOptions() = MangaListFilterOptions()
}
