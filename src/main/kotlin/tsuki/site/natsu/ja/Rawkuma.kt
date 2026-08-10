package tsuki.site.natsu.ja

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.config.ConfigKey
import tsuki.model.MangaParserSource
import tsuki.site.natsu.NatsuParser

@MangaSourceParser("RAWKUMA", "Rawkuma", "ja")
internal class Rawkuma(context: MangaLoaderContext) :
    NatsuParser(context, MangaParserSource.RAWKUMA, 24) {
    override val configKeyDomain = ConfigKey.Domain("rawkuma.net")
}
