package tsuki.site.manhwaz.vi

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.manhwaz.ManhwaZ

@MangaSourceParser("SAYHENTAI", "SayHentai", "vi", ContentType.HENTAI)
internal class SayHentai(context: MangaLoaderContext) :
	ManhwaZ(context, MangaParserSource.SAYHENTAI, "sayhentai.live")
