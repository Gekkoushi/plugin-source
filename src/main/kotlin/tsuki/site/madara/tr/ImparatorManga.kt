package tsuki.site.madara.tr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser
import tsuki.Broken

@Broken("Domain has no DNS records — site is gone")
@MangaSourceParser("IMPARATORMANGA", "ImparatorManga", "tr")
internal class ImparatorManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.IMPARATORMANGA, "www.imparatormanga.com")
