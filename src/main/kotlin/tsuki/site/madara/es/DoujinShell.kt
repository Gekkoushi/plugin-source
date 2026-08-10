package tsuki.site.madara.es

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("DOUJINSHELL", "DoujinShell", "es", ContentType.HENTAI)
internal class DoujinShell(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.DOUJINSHELL, "www.doujinshell.com", 10) {
	override val datePattern = "dd MMMM, yyyy"
	override val listUrl = "doujin/"
	override val tagPrefix = "doujin-genero/"
	override val selectPage = "img:not(.aligncenter)"
}
