package tsuki.site.madara.pt

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@Broken("Domain has no DNS records — site is gone")
@MangaSourceParser("VILLAINESSSCAN", "VillainessScan", "pt", ContentType.HENTAI)
internal class VillainessScan(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.VILLAINESSSCAN, "villainessscan.xyz", pageSize = 10) {
	override val datePattern: String = "dd 'de' MMMM 'de' yyyy"
}
