package tsuki.site.liliana.ja

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.liliana.LilianaParser

@MangaSourceParser("RAW1001", "Raw1001", "ja")
internal class Raw1001(context: MangaLoaderContext) :
	LilianaParser(context, MangaParserSource.RAW1001, "raw1001.net")
