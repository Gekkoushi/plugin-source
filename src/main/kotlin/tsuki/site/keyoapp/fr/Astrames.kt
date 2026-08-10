package tsuki.site.keyoapp.fr

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.keyoapp.KeyoappParser

@Broken("Site is gone — root redirects to an unrelated domain")
@MangaSourceParser("ASTRAMES", "Astrames", "fr")
internal class Astrames(context: MangaLoaderContext) :
	KeyoappParser(context, MangaParserSource.ASTRAMES, "astrames.fr")
