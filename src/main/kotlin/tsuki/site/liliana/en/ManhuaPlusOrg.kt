package tsuki.site.liliana.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.liliana.LilianaParser

@MangaSourceParser("MANHUAPLUSORG", "ManhuaPlus.org", "en")
internal class ManhuaPlusOrg(context: MangaLoaderContext) :
	LilianaParser(context, MangaParserSource.MANHUAPLUSORG, "manhuaplus.org")
