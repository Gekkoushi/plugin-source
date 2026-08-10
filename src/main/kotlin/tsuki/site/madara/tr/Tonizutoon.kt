package tsuki.site.madara.tr

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("TONIZUTOON", "ToniZu.com", "tr", ContentType.HENTAI)
internal class Tonizutoon(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.TONIZUTOON, "tonizu.top") {
	override val datePattern = "dd/mm/yyyy"
}
