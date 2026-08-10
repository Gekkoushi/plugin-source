package tsuki.site.gattsu.pt

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.gattsu.GattsuParser

@MangaSourceParser("HENTAITOKYO", "HentaiTokyo", "pt", ContentType.HENTAI)
internal class HentaiTokyo(context: MangaLoaderContext) :
	GattsuParser(context, MangaParserSource.HENTAITOKYO, "hentaitokyo.net") {
	override val tagUrl = "tags"
}
