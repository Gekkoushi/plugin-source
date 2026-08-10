package tsuki.site.mangareader.es

import org.json.JSONObject
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaChapter
import tsuki.model.MangaPage
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser
import tsuki.util.*
import java.util.ArrayList
import java.util.Base64
import tsuki.Broken

@Broken("Domain has no DNS records — site is gone")
@MangaSourceParser("CATHARSISFANTASY", "CatharsisFantasy", "es")
internal class CatharsisFantasy(context: MangaLoaderContext) :
	MangaReaderParser(
		context,
		MangaParserSource.CATHARSISFANTASY,
		"catharsisfantasy.com",
		pageSize = 30,
		searchPageSize = 10,
	) {

	override suspend fun getPages(chapter: MangaChapter): List<MangaPage> {
		val chapterUrl = chapter.url.toAbsoluteUrl(domain)
		val firstDocs = webClient.httpGet(chapterUrl).parseHtml()
		val urlFinal = firstDocs.selectFirst("iframe")?.src() ?: chapterUrl
		val docs = webClient.httpGet(urlFinal).parseHtml()

		val test = docs.select(selectTestScript)
		if (test.isNullOrEmpty() and !encodedSrc) {
			return docs.select(selectPage).map { img ->
				val url = img.requireSrc().toRelativeUrl(domain)
				MangaPage(
					id = generateUid(url),
					url = url,
					preview = null,
					source = source,
				)
			}
		} else {
			val images = if (encodedSrc) {
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
				JSONObject(decode.substringAfter('(').substringBeforeLast(')'))
					.getJSONArray("sources")
					.getJSONObject(0)
					.getJSONArray("images")

			} else {
				val script = docs.selectFirstOrThrow(selectTestScript)
				JSONObject(script.data().substringAfter('(').substringBeforeLast(')'))
					.getJSONArray("sources")
					.getJSONObject(0)
					.getJSONArray("images")
			}

			val pages = ArrayList<MangaPage>(images.length())
			for (i in 0 until images.length()) {
				pages.add(
					MangaPage(
						id = generateUid(images.getString(i)),
						url = images.getString(i),
						preview = null,
						source = source,
					),
				)
			}
			return pages
		}
	}
}
