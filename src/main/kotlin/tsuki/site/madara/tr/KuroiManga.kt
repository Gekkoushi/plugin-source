package tsuki.site.madara.tr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("KUROIMANGA", "KuroiManga", "tr", ContentType.HENTAI)
internal class KuroiManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.KUROIMANGA, "www.kuroimanga.com") {

	override val withoutAjax = true

	override val datePattern = "d MMMM yyyy"
}
