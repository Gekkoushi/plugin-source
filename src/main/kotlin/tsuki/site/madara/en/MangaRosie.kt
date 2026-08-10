package tsuki.site.madara.en

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@Broken("Domain hijacked — now serves a JS redirect to spam/ads")
@MangaSourceParser("MANGAROSIE", "Toon69", "en")
internal class MangaRosie(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGAROSIE, "toon69.com", pageSize = 16) {
	override val datePattern = "MMMM dd, yyyy"
}
