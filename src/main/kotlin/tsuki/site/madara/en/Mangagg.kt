package tsuki.site.madara.en

import org.jsoup.nodes.Document
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaChapter
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser
import tsuki.util.attrAsRelativeUrl
import tsuki.util.generateUid
import tsuki.util.parseHtml
import tsuki.util.removeSuffix
import tsuki.util.selectFirstOrThrow
import tsuki.util.toAbsoluteUrl
import java.text.SimpleDateFormat

@MangaSourceParser("MANGAGG", "MangaGg", "en")
internal class Mangagg(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGAGG, "mangagg.com") {
	override val tagPrefix = "genre/"
	override val datePattern = "MM/dd/yyyy"

	override suspend fun loadChapters(mangaUrl: String, document: Document): List<MangaChapter> {
		val chapters = mutableListOf<MangaChapter>()
		var page = 1

		while (true) {
			val url = mangaUrl.toAbsoluteUrl(domain).removeSuffix('/') + "/ajax/chapters/?t=$page"
			val doc = webClient.httpPost(url, emptyMap()).parseHtml()
			val pageChapters = parseChapterPage(doc)

			if (pageChapters.isEmpty()) {
				break
			}

			chapters.addAll(pageChapters)
			page++
		}

		return chapters.reversed().mapIndexed { index, chapter ->
			chapter.copy(number = index + 1f)
		}
	}

	private fun parseChapterPage(doc: Document): List<MangaChapter> {
		val dateFormat = SimpleDateFormat(datePattern, sourceLocale)
		return doc.select(selectChapter).map { li ->
			val a = li.selectFirstOrThrow("a")
			val href = a.attrAsRelativeUrl("href")
			val link = href + stylePage
			val dateText = li.selectFirst("a.c-new-tag")?.attr("title") ?: li.selectFirst(selectDate)?.text()
			val name = a.selectFirst("p")?.text() ?: a.ownText()
			MangaChapter(
				id = generateUid(href),
				title = name,
				number = 0f,
				volume = 0,
				url = link,
				uploadDate = parseChapterDate(
					dateFormat,
					dateText,
				),
				source = source,
				scanlator = null,
				branch = null,
			)
		}
	}
}
