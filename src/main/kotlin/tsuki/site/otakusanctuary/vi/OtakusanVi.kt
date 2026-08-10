package tsuki.site.otakusanctuary.vi

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.otakusanctuary.OtakuSanctuaryParser
import tsuki.Broken

@Broken("Original site closed")
@MangaSourceParser("OTAKUSAN_VI", "Otaku Sanctuary (VN)", "vi")
internal class OtakusanVi(context: MangaLoaderContext) :
	OtakuSanctuaryParser(context, MangaParserSource.OTAKUSAN_VI, "otakusan.me") {
	override val selectState = ".table-info tr:contains(Status) td"
	override val lang = "vn"
}
