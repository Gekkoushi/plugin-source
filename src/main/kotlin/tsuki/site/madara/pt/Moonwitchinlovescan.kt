package tsuki.site.madara.pt

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser


@MangaSourceParser("MOONWITCHINLOVESCAN", "MoonWitchinScan", "pt")
internal class Moonwitchinlovescan(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MOONWITCHINLOVESCAN, "moonwitchscan.com.br", 10) {
	override val datePattern: String = "dd 'de' MMMMM 'de' yyyy"
}
