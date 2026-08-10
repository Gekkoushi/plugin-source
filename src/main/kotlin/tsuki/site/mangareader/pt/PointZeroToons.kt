package tsuki.site.mangareader.pt

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser
import tsuki.model.*
import tsuki.util.*

@MangaSourceParser("POINTZEROTOONS", "PointZero Toons", "pt")
internal class PointZeroToons(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.POINTZEROTOONS, "pointzerotoons.com", pageSize = 20, searchPageSize = 10) {

	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)

	override suspend fun getDetails(manga: Manga): Manga {
		val docs = webClient.httpGet(manga.url.toAbsoluteUrl(domain)).parseHtml()
		val chapters = docs.select(selectChapter).mapChapters { index, element ->
			val url = element.selectFirst("a")?.attrAsRelativeUrl("href") ?: return@mapChapters null
			val name = element.selectFirst(".chapternum")?.text() ?: "Chapter ${index + 1}"
			val numChap = findNumChap(name)
			MangaChapter(
				id = generateUid(url),
				title = name,
				url = url,
				number = numChap,
				volume = 0,
				scanlator = null,
				uploadDate = 0,
				branch = null,
				source = source,
			)
		}
		return parseInfo(docs, manga, chapters)
	}

	private fun findNumChap(name: String): Float {
		val regex = Regex("\\d+")
		val num = regex.find(name)
		return num?.value?.toFloat() ?: 0f
	}
}
