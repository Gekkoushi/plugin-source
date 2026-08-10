package tsuki.site.guya.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.guya.GuyaParser

@MangaSourceParser("DANKE", "DankeFursLesen", "en")
internal class Danke(context: MangaLoaderContext) :
	GuyaParser(context, MangaParserSource.DANKE, "danke.moe")
