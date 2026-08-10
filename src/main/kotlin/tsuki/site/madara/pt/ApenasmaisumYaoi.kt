package tsuki.site.madara.pt

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("APENASMAISUMYAOI", "Apenasmaisum Yaoi", "pt", ContentType.HENTAI)
internal class ApenasmaisumYaoi(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.APENASMAISUMYAOI, "apenasmaisumyaoi.com") {
	override val datePattern: String = "dd 'de' MMMMM 'de' yyyy"
}
