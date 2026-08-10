package tsuki.site.zmanga.id

import org.jsoup.nodes.Document
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaChapter
import tsuki.model.MangaParserSource
import tsuki.site.zmanga.ZMangaParser
import tsuki.util.attrAsRelativeUrl
import tsuki.util.generateUid
import tsuki.util.mapChapters
import tsuki.util.selectFirstOrThrow
import java.text.SimpleDateFormat

// Info: Some scans are password-protected
@MangaSourceParser("MAID_ID", "MaidId", "id")
internal class MaidId(context: MangaLoaderContext) :
	ZMangaParser(context, MangaParserSource.MAID_ID, "www.maid.my.id") {

	override suspend fun getChapters(doc: Document): List<MangaChapter> {
		val dateFormat = SimpleDateFormat(datePattern, sourceLocale)
		return doc.body().select(selectChapter).mapChapters(reversed = true) { i, li ->
			val a = li.selectFirstOrThrow("a")
			val href = a.attrAsRelativeUrl("href")
			val dateText = li.selectFirst(selectDate)?.text()
			val numChapter = li.selectFirstOrThrow(".flexch-infoz span").html().substringAfterLast("Chapter ")
				.substringBefore("<span")
			MangaChapter(
				id = generateUid(href),
				title = null,
				number = numChapter.toFloatOrNull() ?: (i + 1f),
				volume = 0,
				url = href,
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
