package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("MANGA_DISTRICT", "MangaDistrict", "en", ContentType.HENTAI)
internal class MangaDistrict(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGA_DISTRICT, "mangadistrict.com", pageSize = 30) {

	override val tagPrefix = "publication-genre/"
}
