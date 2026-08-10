package tsuki.site.mangareader.tr

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@Broken("Domain has no DNS records — site is gone")
@MangaSourceParser("MANGACIM", "Mangacim", "tr")
internal class Mangacim(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.MANGACIM, "mangacim.com.tr", pageSize = 20, searchPageSize = 20) {
	override val datePattern = "MMM d, yyyy"
}
