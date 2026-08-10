package tsuki.site.mangareader.ar

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.Manga
import tsuki.model.MangaChapter
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser
import tsuki.util.*

@MangaSourceParser("MANJANOON", "Manjanoon", "ar")
internal class Manjanoon(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.MANJANOON, "vrnoin.site", pageSize = 21, searchPageSize = 10) {

	override suspend fun getDetails(manga: Manga): Manga {
		val docs = webClient.httpGet(manga.url.toAbsoluteUrl(domain)).parseHtml()
		val chapters = docs.select(selectChapter).mapChapters(reversed = true) { index, element ->
			val url = element.selectFirst("a")?.attrAsRelativeUrl("href") ?: return@mapChapters null
			MangaChapter(
				id = generateUid(url),
				title = element.selectFirst(".chapternum")?.textOrNull(),
				url = url,
				number = index + 1f,
				volume = 0,
				scanlator = null,
				uploadDate = 0,
				branch = null,
				source = source,
			)
		}
		return parseInfo(docs, manga, chapters)
	}
}
