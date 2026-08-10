package tsuki.site.madara.pt

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("NINJASCAN", "NinjaComics", "pt")
internal class NinjaScan(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.NINJASCAN, "ninjacomics.xyz") {
	override val datePattern: String = "dd 'de' MMMMM 'de' yyyy"
}
