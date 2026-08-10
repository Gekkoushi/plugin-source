package tsuki.site.fmreader.ja

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.fmreader.FmreaderParser

@MangaSourceParser("WELOMA", "Weloma", "ja")
internal class Weloma(context: MangaLoaderContext) :
	FmreaderParser(context, MangaParserSource.WELOMA, "weloma.art")
