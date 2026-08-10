package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("KUMASCANS", "Retsu", "en")
internal class KumaScans(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.KUMASCANS, "retsu.org") {
	override val tagPrefix = "genre/"
}
