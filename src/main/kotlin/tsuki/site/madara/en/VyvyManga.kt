package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser
import tsuki.Broken

@Broken("Cloudflare reports origin server unreachable") // It has become obsolete and has been replaced by the new VyManga parser.
@MangaSourceParser("VYVYMANGA", "VyvyManga", "en")
internal class VyvyManga(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.VYVYMANGA, "vyvymanga.org")
