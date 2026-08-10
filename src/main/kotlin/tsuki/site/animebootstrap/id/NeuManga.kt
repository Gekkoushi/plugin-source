package tsuki.site.animebootstrap.id

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.animebootstrap.AnimeBootstrapParser

@MangaSourceParser("NEUMANGA", "NeuManga.xyz", "id")
internal class NeuManga(context: MangaLoaderContext) :
	AnimeBootstrapParser(context, MangaParserSource.NEUMANGA, "neumanga.xyz")
