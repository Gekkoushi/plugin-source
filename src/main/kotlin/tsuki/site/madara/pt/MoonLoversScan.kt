package tsuki.site.madara.pt

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("MOONLOVERSSCAN", "MoonLoversScan", "pt", ContentType.HENTAI)
internal class MoonLoversScan(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MOONLOVERSSCAN, "moonloversscan.com.br", 10)
