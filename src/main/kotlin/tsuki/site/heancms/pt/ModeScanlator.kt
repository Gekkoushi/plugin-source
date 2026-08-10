package tsuki.site.heancms.pt

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.*
import tsuki.site.heancms.HeanCms

@Broken("Domain hijacked — now serves a JS redirect to spam/ads")
@MangaSourceParser("MODESCANLATOR", "ModeScanlator", "pt")
internal class ModeScanlator(context: MangaLoaderContext) :
	HeanCms(context, MangaParserSource.MODESCANLATOR, "site.modescanlator.net") {
	override val apiPath = "api.modescanlator.net"
}

