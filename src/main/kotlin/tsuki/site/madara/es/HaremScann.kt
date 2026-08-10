package tsuki.site.madara.es

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser
import tsuki.Broken

@Broken("Domain haremscann.es repurposed — redirects to an unrelated site (mobile.mytsubaki.com)")
@MangaSourceParser("HAREMSCANN", "HaremScann", "es")
internal class HaremScann(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.HAREMSCANN, "haremscann.es") {
	override val postReq = true
}
