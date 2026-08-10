package tsuki.site.natsu.id

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.config.ConfigKey
import tsuki.model.MangaPage
import tsuki.model.MangaChapter
import tsuki.model.MangaParserSource
import tsuki.site.natsu.NatsuParser
import tsuki.util.generateUid
import tsuki.util.parseHtml
import tsuki.util.requireSrc
import tsuki.util.toAbsoluteUrl
import tsuki.util.toRelativeUrl

@MangaSourceParser("KIRYUU", "Kiryuu", "id")
internal class Kiryuu(context: MangaLoaderContext) :
	NatsuParser(context, MangaParserSource.KIRYUU, 24) {
	override val configKeyDomain = ConfigKey.Domain("v5.kiryuu.to")

	override suspend fun getPages(chapter: MangaChapter): List<MangaPage> {
		val doc = webClient.httpGet(chapter.url.toAbsoluteUrl(domain)).parseHtml()
		// Images are in a section with data-image-data attribute
		return doc.select("section[data-image-data] img").map { img ->
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
