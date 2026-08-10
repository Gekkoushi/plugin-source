package tsuki.site.mangareader.id

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser
import java.util.*

@Broken("Domain has no DNS records — site is gone")
@MangaSourceParser("ALCEASCAN", "AlceaScan", "id")
internal class AlceaScan(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.ALCEASCAN, "alceacomic.my.id", pageSize = 20, searchPageSize = 10) {
	override val sourceLocale: Locale = Locale.ENGLISH
}