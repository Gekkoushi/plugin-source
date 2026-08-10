package tsuki.site.iken.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.iken.IkenParser
import tsuki.model.*
import tsuki.util.*

@MangaSourceParser("PHILIASCANS", "PhiliaScans", "en")
internal class PhiliaScans(context: MangaLoaderContext) :
	IkenParser(context, MangaParserSource.PHILIASCANS, "philiascans.org", 18) {

	override suspend fun getPages(chapter: MangaChapter): List<MangaPage> {
		val fullUrl = chapter.url.toAbsoluteUrl(domain)
		val doc = webClient.httpGet(fullUrl).parseHtml()
		return doc.select(selectPages).map { img ->
			val url = img.requireSrc()
			MangaPage(
				id = generateUid(url),
				url = url,
				preview = null,
				source = source,
			)
		}
	}
}