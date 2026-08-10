package tsuki.site.cupfox.vi

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.cupfox.CupFoxParser

@Broken("Domain has no DNS records — site is gone")
@MangaSourceParser("OIOIVN", "OioiVn", "vi")
internal class OioiVn(context: MangaLoaderContext) :
	CupFoxParser(context, MangaParserSource.OIOIVN, "oioivn.com")
