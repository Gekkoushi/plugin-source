package tsuki.site.madara.en

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@Broken("Domain hijacked — now serves a JS redirect to spam/ads")
@MangaSourceParser("KIARA18", "Kiara18", "en", ContentType.HENTAI)
internal class Kiara18(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.KIARA18, "18.kiara.cool")
