package tsuki.site.madara.es

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("LECTORMANGA", "LectorManga", "es")
internal class LectorManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.LECTORMANGA, "lectormangaa.com") {
	override val listUrl = "biblioteca/"
	override val tagPrefix = "comics-genero/"
}
