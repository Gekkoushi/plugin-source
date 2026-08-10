package tsuki.site.madara.ja

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser
import tsuki.Broken

@Broken("Site is gone — root redirects to an unrelated domain")
@MangaSourceParser("RAWXZ", "RawXz", "ja")
internal class RawXz(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.RAWXZ, "rawxz.ac") {
	override val listUrl = "jp-manga/"
}
