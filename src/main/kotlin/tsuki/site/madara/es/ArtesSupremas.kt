package tsuki.site.madara.es

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@Broken("Domain parked — landing page only, no manga content")
@MangaSourceParser("ARTESSUPREMAS", "ArtesSupremas", "es")
internal class ArtesSupremas(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.ARTESSUPREMAS, "artessupremas.com") {
	override val datePattern = "dd/MM/yyyy"
}
