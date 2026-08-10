package tsuki.site.otakusanctuary.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.otakusanctuary.OtakuSanctuaryParser
import tsuki.Broken

@Broken("Original site closed")
@MangaSourceParser("OTAKUSAN_EN", "Otaku Sanctuary (EN)", "en")
internal class OtakusanEn(context: MangaLoaderContext) :
	OtakuSanctuaryParser(context, MangaParserSource.OTAKUSAN_EN, "otakusan.me") {
	override val lang = "us"
	override val selectState = ".table-info tr:contains(Status) td"
}
