package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.exception.ParseException
import tsuki.model.*
import tsuki.site.madara.MadaraParser
import tsuki.util.*

@MangaSourceParser("MANHUAPLUS", "ManhuaPlus", "en")
internal class Manhuaplus(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANHUAPLUS, "manhuaplus.com") {

	override val withoutAjax = true

	override suspend fun getPages(chapter: MangaChapter): List<MangaPage> {
		val fullUrl = chapter.url.toAbsoluteUrl(domain)
		val doc = webClient.httpGet(fullUrl).parseHtml()
		val root = doc.body().selectFirst("div.main-col-inner")?.selectFirst("div.reading-content")
			?: throw ParseException("Root not found", fullUrl)
		return root.select("img").map { img ->
			val url = img.requireSrc().toRelativeUrl(domain)
			MangaPage(
				id = generateUid(url),
				url = url,
				preview = null,
				source = source,
			)
		}
	}
}
