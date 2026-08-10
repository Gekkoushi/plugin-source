package tsuki.site.madara.fr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("HENTAISCANTRADVF", "Hentai-Scantrad", "fr", ContentType.HENTAI)
internal class HentaiScantradVf(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.HENTAISCANTRADVF, "hentai.scantrad-vf.cc") {
	override val datePattern = "d MMMM, yyyy"
}
