package tsuki.site.madara.es

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser
import tsuki.Broken

@Broken("Site is online but parser is broken — layout/API changed, needs rewrite")
@MangaSourceParser("MONARCAMANGA", "MonarcaManga", "es")
internal class MonarcaManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MONARCAMANGA, "visormonarca.com") {
	override val tagPrefix = "manga-generos/"
}
