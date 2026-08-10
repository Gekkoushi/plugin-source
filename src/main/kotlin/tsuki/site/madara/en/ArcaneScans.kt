package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser
import tsuki.Broken

@Broken("Site is gone — root redirects to an unrelated domain")
@MangaSourceParser("ARCANESCANS", "ArcaneScans", "en")
internal class ArcaneScans(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.ARCANESCANS, "arcanescans.com", 10)
