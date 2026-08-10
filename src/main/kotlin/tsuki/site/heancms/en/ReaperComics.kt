package tsuki.site.heancms.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.*
import tsuki.site.heancms.HeanCms
import tsuki.Broken

@Broken("Site is in maintenance mode — parser cannot fetch while site is offline")
@MangaSourceParser("REAPERCOMICS", "ReaperComics", "en")
internal class ReaperComics(context: MangaLoaderContext) :
	HeanCms(context, MangaParserSource.REAPERCOMICS, "reaperscans.com") {
	override val cdn = "media.reaperscans.com/file/4SRBHm//"
	override val paramsUpdated = "updated_at"
	override val selectPages = ".flex > img"

	override fun reqUrl(seriesId: Long): String {
		return "https://$apiPath/chapters/$seriesId?page=1&perPage=9999&order=desc"
	}
}
