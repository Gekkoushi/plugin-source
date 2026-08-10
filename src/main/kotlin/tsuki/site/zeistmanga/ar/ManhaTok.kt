package tsuki.site.zeistmanga.ar

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.zeistmanga.ZeistMangaParser

@MangaSourceParser("MANHATOK", "ManhaTok", "ar")
internal class ManhaTok(context: MangaLoaderContext) :
	ZeistMangaParser(context, MangaParserSource.MANHATOK, "manhatok.blogspot.com") {
	override val selectPage = "#seoneurons-target img"
}
