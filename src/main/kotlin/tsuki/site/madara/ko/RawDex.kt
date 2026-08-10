package tsuki.site.madara.ko

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser
import java.util.*

@MangaSourceParser("RAWDEX", "RawDex", "ko", ContentType.HENTAI)
internal class RawDex(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.RAWDEX, "rawdex.net", 40) {
	override val sourceLocale: Locale = Locale.ENGLISH
}
