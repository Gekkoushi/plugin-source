package tsuki.site.onemanga.fr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.onemanga.OneMangaParser

@MangaSourceParser("FIREFORCE", "FireForce", "fr")
internal class FireForce(context: MangaLoaderContext) :
	OneMangaParser(context, MangaParserSource.FIREFORCE, "fireforce.fr")
