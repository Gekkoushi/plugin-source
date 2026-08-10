package tsuki.site.madara.pt

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@Broken("Site is online but parser is broken — layout/API changed, needs rewrite")
@MangaSourceParser("MANGANANQUIM", "MangaNanquim", "pt")
internal class MangaNanquim(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGANANQUIM, "mangananquim.site", 10) {
	override val datePattern: String = "d 'de' MMMM 'de' yyyy"
	override val listUrl = "ler-manga/"
	override val tagPrefix = "manga-genero/"
}
