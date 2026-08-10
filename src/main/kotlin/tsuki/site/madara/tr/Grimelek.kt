package tsuki.site.madara.tr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser
import tsuki.util.getCookies

//This source requires an account.
@MangaSourceParser("GRIMELEK", "SiyahMelek", "tr")
internal class Grimelek(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.GRIMELEK, "siyahmelek.pro", 20) {
	override val datePattern = "d MMMM yyyy"
	override val listUrl = "seri/"
	override val authUrl: String
		get() = "https://${domain}"

	override suspend fun isAuthorized(): Boolean {
		return context.cookieJar.getCookies(domain).any {
			it.name.contains("wordpress_logged")
		}
	}
}
