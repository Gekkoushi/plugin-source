package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser
import tsuki.Broken

@Broken("Original site closed")
@MangaSourceParser("STKISSMANGABLOG", "1StKissManga.net", "en")
internal class StkissMangaBlog(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.STKISSMANGABLOG, "1stkissmanga.org", 20) {
            override val postReq = true
      }
