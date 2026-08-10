package tsuki.site.madara.en

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@Broken("Domain hijacked — now serves a JS redirect to spam/ads")
@MangaSourceParser("TREE_MANGA", "TreeManga", "en")
internal class TreeManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.TREE_MANGA, "treemanga.com") {
	override val datePattern = "MM/dd/yyyy"
}
