package tsuki.site.mangareader.es

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser
import tsuki.Broken

@Broken("Domain has no DNS records — site is gone")
@MangaSourceParser("CARTELDEMANHWAS", "Cartel De Manhwas", "es")
internal class CartelDeManhwas(context: MangaLoaderContext) :
	MangaReaderParser(
		context,
		MangaParserSource.CARTELDEMANHWAS,
		"cartelmanhwas.net",
		pageSize = 20,
		searchPageSize = 20,
	) {
	override val listUrl = "/series"
	override val datePattern = "MMM d, yyyy"
}
