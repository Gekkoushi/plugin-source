package tsuki.site.madara.es

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser
import tsuki.Broken

@Broken("Cloudflare origin TLS error (5xx) — site misconfigured or dead")
@MangaSourceParser("TECNOPROJECTS", "TecnoProjects", "es")
internal class TecnoProjects(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.TECNOPROJECTS, "tecnoprojects.xyz") {
	override val datePattern = "dd 'de' MMMM 'de' yyyy"
}
