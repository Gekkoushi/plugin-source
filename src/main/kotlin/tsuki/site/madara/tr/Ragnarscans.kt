package tsuki.site.madara.tr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser
import tsuki.Broken

@Broken("Blocked by Cloudflare challenge")
@MangaSourceParser("RAGNARSCANS", "Ragnarscans", "tr")
internal class Ragnarscans(context: MangaLoaderContext) :
    MadaraParser(context, MangaParserSource.RAGNARSCANS, "ragnarscans.com", pageSize = 10) {
	  override val datePattern = "d MMMM yyyy"
}
