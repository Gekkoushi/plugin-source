package tsuki.site.gallery.zh

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.model.ContentType
import tsuki.model.MangaListFilterOptions
import tsuki.site.gallery.GalleryParser

@MangaSourceParser("XIUTAKU", "Xiutaku", "zh", type = ContentType.OTHER)
internal class Xiutaku(context: MangaLoaderContext) :
    GalleryParser(context, MangaParserSource.XIUTAKU, "xiutaku.com") {

    override suspend fun getFilterOptions(): MangaListFilterOptions =
		MangaListFilterOptions(availableTags = fetchTags())
}
