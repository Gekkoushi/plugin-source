package tsuki.site.ru.rulib

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource

@MangaSourceParser("YAOILIB", "SlashLib", "ru")
internal class SlashLibParser(context: MangaLoaderContext) : LibSocialParser(
	context = context,
	source = MangaParserSource.YAOILIB,
	siteId = 2,
	siteDomains = arrayOf("v2.slashlib.me"),
)
