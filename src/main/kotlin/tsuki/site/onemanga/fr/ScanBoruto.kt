package tsuki.site.onemanga.fr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.onemanga.OneMangaParser

@MangaSourceParser("SCANBORUTO", "ScanBoruto", "fr")
internal class ScanBoruto(context: MangaLoaderContext) :
	OneMangaParser(context, MangaParserSource.SCANBORUTO, "scanboruto.fr")
