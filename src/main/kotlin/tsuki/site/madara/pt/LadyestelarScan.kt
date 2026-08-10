package tsuki.site.madara.pt

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@Broken("Domain has no DNS records — site is gone")
@MangaSourceParser("LADYESTELARSCAN", "LadyEstelarScan", "pt")
internal class LadyestelarScan(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.LADYESTELARSCAN, "ladyestelarscan.com.br", 10) {
	override val datePattern: String = "dd/MM/yyyy"
}
