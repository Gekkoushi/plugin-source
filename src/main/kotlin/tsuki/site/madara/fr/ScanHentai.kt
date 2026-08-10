package tsuki.site.madara.fr

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@Broken("Domain has no DNS records — site is gone")
@MangaSourceParser("SCANHENTAI", "ScanHentai", "fr", ContentType.HENTAI)
internal class ScanHentai(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.SCANHENTAI, "scan-hentai.fr") {
	override val datePattern = "dd/MM/yyyy"
}
