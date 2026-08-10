package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaChapter
import tsuki.model.MangaPage
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser
import tsuki.util.*

@MangaSourceParser("MANHWADEN", "ManhwaDen", "en", ContentType.HENTAI)
internal class Manhwaden(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANHWADEN, "www.manhwaden.com", 10) {

	override val selectPage = "p img[src]"

	override suspend fun getPages(chapter: MangaChapter): List<MangaPage> {
		val fullUrl = chapter.url.toAbsoluteUrl(domain)
		val doc = webClient.httpGet(fullUrl).parseHtml()
		val root = doc.body().selectFirstOrThrow(selectBodyPage)
		return root.select(selectPage).map { img ->
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
