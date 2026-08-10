package tsuki.site.madara.pt

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("WICKEDWITCHSCAN", "WickedWitchScan", "pt")
internal class WickedWitchScan(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.WICKEDWITCHSCAN, "wicked-witch-scan.com", pageSize = 10) {
	override val postReq = true
}
