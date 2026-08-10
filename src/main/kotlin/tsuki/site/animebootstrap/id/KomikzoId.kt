package tsuki.site.animebootstrap.id

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.animebootstrap.AnimeBootstrapParser

@MangaSourceParser("KOMIKZOID", "KomikzoId", "id")
internal class KomikzoId(context: MangaLoaderContext) :
	AnimeBootstrapParser(context, MangaParserSource.KOMIKZOID, "komikzoid.id")
