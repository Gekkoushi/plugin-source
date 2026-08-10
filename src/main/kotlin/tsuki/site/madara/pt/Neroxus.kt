package tsuki.site.madara.pt

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("NEROXUS", "Neroxus", "pt")
internal class Neroxus(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.NEROXUS, "neroxus.com.br", 10) {
	override val datePattern = "MMM d, yyyy"
}
