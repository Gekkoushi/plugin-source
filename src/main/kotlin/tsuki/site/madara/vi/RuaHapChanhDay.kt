package tsuki.site.madara.vi

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("RUAHAPCHANHDAY", "Rùa Hấp Chanh Dây", "vi")
internal class RuaHapChanhDay(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.RUAHAPCHANHDAY, "ruahapchanhday1.com", 30) {
	override val datePattern = "dd/MM/yyyy"
}
