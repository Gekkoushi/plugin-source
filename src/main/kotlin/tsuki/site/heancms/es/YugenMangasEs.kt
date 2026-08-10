package tsuki.site.heancms.es

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.*
import tsuki.site.heancms.HeanCms

@Broken("Domain lectorikigai.acamu.net expired — redirects to a parking page")
@MangaSourceParser("YUGEN_MANGAS_ES", "YugenMangas.lat", "es", ContentType.HENTAI)
internal class YugenMangasEs(context: MangaLoaderContext) :
	HeanCms(context, MangaParserSource.YUGEN_MANGAS_ES, "lectorikigai.acamu.net")
