package tsuki.site.gallery.all

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.config.ConfigKey
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.gallery.GalleryParser

@MangaSourceParser("BUONDUA", "Buon Dua", "vi", type = ContentType.OTHER)
internal class BuonDua(context: MangaLoaderContext) :
    GalleryParser(context, MangaParserSource.BUONDUA, "buondua.com") {

    override val configKeyDomain: ConfigKey.Domain = ConfigKey.Domain(
        "buondua.com",
        "buondua.us",
    )
}
