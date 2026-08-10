package tsuki.site.mangareader.fr

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@Broken("Root returns HTTP 404 — site gone or restructured")
@MangaSourceParser("PANTHEONSCAN_FR", "PantheonScan.fr", "fr")
internal class PantheonScanFr(context: MangaLoaderContext) :
	MangaReaderParser(
		context,
		MangaParserSource.PANTHEONSCAN_FR,
		"www.pantheon-scan.fr",
		pageSize = 40,
		searchPageSize = 10,
	)
