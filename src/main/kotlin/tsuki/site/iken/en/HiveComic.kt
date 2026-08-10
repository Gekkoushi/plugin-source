package tsuki.site.iken.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.iken.IkenParser

@MangaSourceParser("HIVECOMIC", "HiveComic", "en")
internal class HiveComic(context: MangaLoaderContext) :
	IkenParser(context, MangaParserSource.HIVECOMIC, "hivetoons.org", 18, true)
