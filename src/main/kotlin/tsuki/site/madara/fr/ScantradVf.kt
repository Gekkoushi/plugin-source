package tsuki.site.madara.fr

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@Broken("Site is online but parser is broken — layout/API changed, needs rewrite")
@MangaSourceParser("SCANTRADVF", "Scantrad-Vf", "fr")
internal class ScantradVf(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.SCANTRADVF, "scantrad-vf.me") {
	override val datePattern = "d MMMM yyyy"
	override val tagPrefix = "genre/"
}
