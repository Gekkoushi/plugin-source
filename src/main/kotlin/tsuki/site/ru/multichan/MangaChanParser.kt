package tsuki.site.ru.multichan

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.config.ConfigKey
import tsuki.model.MangaParserSource

@MangaSourceParser("MANGACHAN", "Манга-тян", "ru")
internal class MangaChanParser(context: MangaLoaderContext) : ChanParser(context, MangaParserSource.MANGACHAN) {
	override val configKeyDomain = ConfigKey.Domain("manga-chan.me")
}
