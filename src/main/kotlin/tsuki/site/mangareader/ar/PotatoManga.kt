package tsuki.site.mangareader.ar

import org.json.JSONObject
import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaChapter
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaPage
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser
import tsuki.util.*

@Broken("Site is online but parser is broken — layout/API changed, needs rewrite")
@MangaSourceParser("POTATOMANGA", "PotatoManga", "ar")
internal class PotatoManga(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.POTATOMANGA, "potatomanga.xyz", pageSize = 30, searchPageSize = 10) {
	override val listUrl = "/series"
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)

	override suspend fun getPages(chapter: MangaChapter): List<MangaPage> {
		val chapterUrl = chapter.url.toAbsoluteUrl(domain)
		val docs = webClient.httpGet(chapterUrl).parseHtml()
		val script = docs.selectFirstOrThrow(selectTestScript)
		val images = JSONObject(script.data().substringAfter('(').substringBeforeLast(')'))
			.getJSONArray("sources")
			.getJSONObject(0)
			.getJSONArray("images")
		val pages = ArrayList<MangaPage>(images.length())
		for (i in 0 until images.length()) {
			pages.add(
				MangaPage(
					id = generateUid(images.getString(i).replace("\\", "")),
					url = images.getString(i).replace("\\", ""),
					preview = null,
					source = source,
				),
			)
		}
		return pages
	}
}
