package tsuki.site.heancms.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.*
import tsuki.site.heancms.HeanCms

@MangaSourceParser("OMEGASCANS", "OmegaScans", "en", ContentType.HENTAI)
internal class OmegaScans(context: MangaLoaderContext) :
	HeanCms(context, MangaParserSource.OMEGASCANS, "omegascans.org")
