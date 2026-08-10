package tsuki.site.madara.es

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("TRADUCCIONESAMISTOSAS", "TraduccionesAmistosas", "es")
internal class TraduccionesAmistosas(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.TRADUCCIONESAMISTOSAS, "traduccionesamistosas.topmanhuas.org", 10) {
	override val datePattern = "d 'de' MMMMM 'de' yyyy"
}
