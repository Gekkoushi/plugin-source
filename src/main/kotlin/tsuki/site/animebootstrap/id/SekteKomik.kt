package tsuki.site.animebootstrap.id

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.animebootstrap.AnimeBootstrapParser

@MangaSourceParser("SEKTEKOMIK", "SekteKomik", "id")
internal class SekteKomik(context: MangaLoaderContext) :
	AnimeBootstrapParser(context, MangaParserSource.SEKTEKOMIK, "sektekomik.xyz")
