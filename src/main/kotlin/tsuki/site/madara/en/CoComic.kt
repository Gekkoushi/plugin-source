package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.model.MangaTag
import tsuki.site.madara.MadaraParser

@MangaSourceParser("COCOMIC", "CoComic", "en", ContentType.HENTAI)
internal class CoComic(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.COCOMIC, "cocomic.co") {

	override val filterCapabilities: MangaListFilterCapabilities
		get() = MangaListFilterCapabilities(
			isSearchSupported = true,
		)

	override suspend fun fetchAvailableTags(): Set<MangaTag> = emptySet()
}
