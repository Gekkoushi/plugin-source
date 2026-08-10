package tsuki.site.madara.es

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser
import tsuki.Broken

@Broken("Server not responding — connection times out") // Host error
@MangaSourceParser("STICKHORSE", "StickHorse", "es")
internal class Stickhorse(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.STICKHORSE, "www.stickhorse.cl") {
	override val postReq = true
}
