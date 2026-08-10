package tsuki.site.onemanga.fr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.onemanga.OneMangaParser

@MangaSourceParser("ONEPIECESCAN", "OnePieceScan", "fr")
internal class OnePieceScan(context: MangaLoaderContext) :
	OneMangaParser(context, MangaParserSource.ONEPIECESCAN, "onepiecescan.fr")
