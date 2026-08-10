package tsuki.site.madara.zh

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser
import tsuki.Broken

@Broken("Blocked by Cloudflare challenge")
@MangaSourceParser("BAKAMH", "Bakamh", "zh")
internal class Bakamh(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.BAKAMH, "bakamh.com") {
	override val datePattern = "YYYY 年 M 月 d 日"
}
