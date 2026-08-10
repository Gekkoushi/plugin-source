package tsuki.site.mangareader.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("DEXHENTAI", "DexHentai", "en", ContentType.HENTAI)
internal class DexHentai(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.DEXHENTAI, "dexhentai.com", 40, 36)
