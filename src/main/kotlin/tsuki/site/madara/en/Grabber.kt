package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("GRABBER", "Grabber", "en", ContentType.COMICS)
internal class Grabber(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.GRABBER, "grabber.zone", 20) {
	override val tagPrefix = "type/"
	override val listUrl = "comics/"
	override val datePattern = "dd.MM.yyyy"
}
