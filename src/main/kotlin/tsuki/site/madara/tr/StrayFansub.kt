package tsuki.site.madara.tr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser
import tsuki.Broken

@Broken("Domain has no DNS records — site is gone") // The website is not functioning and displays the Plesk panel
@MangaSourceParser("STRAYFANSUB", "StrayFansub", "tr")
internal class StrayFansub(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.STRAYFANSUB, "strayfansub.com", 16) {
	override val tagPrefix = "seri-turu/"
}
