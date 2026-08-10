package tsuki.site.madara.en

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@Broken("Domain hijacked — now serves a JS redirect to spam/ads")
@MangaSourceParser("MMSCANS", "MmScans", "en")
internal class MmScans(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MMSCANS, "mm-scans.org") {
	override val selectChapter = "li.chapter-li"
	override val selectDesc = "div.summary-text"
	override val withoutAjax = true
}
