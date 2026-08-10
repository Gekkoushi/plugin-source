package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser
import tsuki.Broken

@Broken("Original site closed")
@MangaSourceParser("SCANSRAW", "AquaScans.com", "en")
internal class Scansraw(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.SCANSRAW, "aquascans.com", 10)
