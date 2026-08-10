package tsuki.site.iken.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.iken.IkenParser

@MangaSourceParser("MAGUSMANGA", "MagusToon", "en")
internal class MagusToon(context: MangaLoaderContext) :
        IkenParser(context, MangaParserSource.MAGUSMANGA, "magustoon.org", 18, true)
