package tsuki.site.mangareader.pt

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("SILENCESCAN", "SilenceScan", "pt", ContentType.HENTAI)
internal class Silencescan(context: MangaLoaderContext) :
	MangaReaderParser(
		context,
		MangaParserSource.SILENCESCAN,
		"silencescan.com.br",
		pageSize = 35,
		searchPageSize = 35,
	) {
	override val datePattern = "MMM d, yyyy"
}
