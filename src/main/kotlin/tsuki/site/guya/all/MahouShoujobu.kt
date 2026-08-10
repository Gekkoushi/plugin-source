package tsuki.site.guya.all

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.guya.GuyaParser

@MangaSourceParser("MAHOUSHOUJOBU", "MahouShoujobu")
internal class MahouShoujobu(context: MangaLoaderContext) :
	GuyaParser(context, MangaParserSource.MAHOUSHOUJOBU, "mahoushoujobu.com")
