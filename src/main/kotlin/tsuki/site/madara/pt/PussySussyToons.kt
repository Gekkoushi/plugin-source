package tsuki.site.madara.pt

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("PUSSYSUSSYTOONS", "PussySussyToons", "pt", ContentType.HENTAI)
internal class PussySussyToons(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.PUSSYSUSSYTOONS, "pussy.sussytoons.com")
