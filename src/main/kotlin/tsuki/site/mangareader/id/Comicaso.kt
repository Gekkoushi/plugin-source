package tsuki.site.mangareader.id

import org.json.JSONObject
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaChapter
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaPage
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser
import tsuki.util.generateUid
import tsuki.util.parseHtml
import tsuki.util.toAbsoluteUrl
import java.util.*

@MangaSourceParser("COMICASO", "Comicaso", "id")
internal class Comicaso(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.COMICASO, "comicaso.xyz", pageSize = 20, searchPageSize = 10) {
	override val encodedSrc = true

	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)

	override suspend fun getPages(chapter: MangaChapter): List<MangaPage> {
		val chapterUrl = chapter.url.toAbsoluteUrl(domain)
		val docs = webClient.httpGet(chapterUrl).parseHtml()

		val script = docs.select(selectScript)
		var decode = ""
		for (i in script) {
			if (i.attr("src").startsWith("data:text/javascript;base64,")) {
				decode = Base64.getDecoder().decode(i.attr("src").replace("data:text/javascript;base64,", ""))
					.decodeToString()
				if (decode.startsWith("ts_reader.run")) {
					break
				}
			}
		}
		val images = JSONObject(decode.substringAfter('(').substringBeforeLast(')'))
			.getJSONArray("sources")
			.getJSONObject(0)
			.getJSONArray("images")

		val pages = ArrayList<MangaPage>(images.length())
		for (i in 0 until images.length()) {
			pages.add(
				MangaPage(
					id = generateUid(images.getString(i)),
					url = images.getString(i).replace("http:", "https:"),
					preview = null,
					source = source,
				),
			)
		}
		return pages
	}
}
