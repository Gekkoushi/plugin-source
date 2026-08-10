package tsuki.site.madara.vi

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@Broken("Site is online but parser is broken — layout/API changed, needs rewrite")
@MangaSourceParser("PINKTEACOMIC", "PinkTeaComic", "vi")
internal class PinkTeaComic(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.PINKTEACOMIC, "pinkteacomics.com") {
	override val datePattern = "d MMMM, yyyy"
}
