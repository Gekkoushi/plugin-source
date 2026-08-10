package tsuki.site.madara.tr

import org.jsoup.nodes.Document
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaChapter
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser
import tsuki.util.attrAsRelativeUrlOrNull
import tsuki.util.generateUid
import tsuki.util.mapChapters
import tsuki.util.parseFailed
import tsuki.util.parseHtml
import tsuki.util.removeSuffix
import tsuki.util.toAbsoluteUrl
import java.text.SimpleDateFormat

@MangaSourceParser("LAVINIAFANSUB", "LaviniaFansub", "tr", ContentType.HENTAI)
internal class LaviniaFansub(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.LAVINIAFANSUB, "laviniafansub.site", 18) {
	override val datePattern = "dd/MM/yyyy"

	override suspend fun loadChapters(mangaUrl: String, document: Document): List<MangaChapter> {
		val doc = if (postReq) {
			val mangaId = document.select("div#manga-chapters-holder").attr("data-id")
			val url = "https://$domain/wp-admin/admin-ajax.php"
			val postData = "action=manga_get_chapters&manga=$mangaId"
			webClient.httpPost(url, postData).parseHtml()
		} else {
			val url = mangaUrl.toAbsoluteUrl(domain).removeSuffix('/') + "/ajax/chapters/"
			webClient.httpPost(url, emptyMap()).parseHtml()
		}
		val dateFormat = SimpleDateFormat(datePattern, sourceLocale)
		return doc.select(selectChapter).mapChapters(reversed = true) { i, li ->
			val a = li.selectFirst("a:not(:has(img))")
			val href = a?.attrAsRelativeUrlOrNull("href") ?: li.parseFailed("Link is missing")
			val link = href + stylePage
			val dateText = li.selectFirst("a.c-new-tag")?.attr("title") ?: li.selectFirst(selectDate)?.text()
			val name = a.selectFirst("p")?.text() ?: a.ownText()
			MangaChapter(
				id = generateUid(href),
				url = link,
				title = name,
				number = i + 1f,
				volume = 0,
				branch = null,
				uploadDate = parseChapterDate(
					dateFormat,
					dateText,
				),
				scanlator = null,
				source = source,
			)
		}
	}
}
