package tsuki.site.madara.es

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser
import tsuki.Broken

@Broken("Site is online but parser is broken — layout/API changed, needs rewrite")
@MangaSourceParser("DAPROB", "Daprob", "es")
internal class Daprob(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.DAPROB, "daprob.com") {
	override val datePattern = "d 'de' MMMMM 'de' yyyy"
}
