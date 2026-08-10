package tsuki.site.madara.pt

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentRating
import tsuki.model.Manga
import tsuki.model.MangaChapter
import tsuki.model.MangaParserSource
import tsuki.model.MangaState
import tsuki.model.RATING_UNKNOWN
import tsuki.site.madara.MadaraParser
import tsuki.util.attrAsRelativeUrl
import tsuki.util.generateUid
import tsuki.util.mapChapters
import tsuki.util.parseHtml
import tsuki.util.removeSuffix
import tsuki.util.selectFirstOrThrow
import tsuki.util.textOrNull
import tsuki.util.toAbsoluteUrl
import java.text.SimpleDateFormat

@MangaSourceParser("HUNTERSSCAN", "HuntersScan", "pt")
internal class HuntersScan(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.HUNTERSSCAN, "readhunters.xyz", pageSize = 50) {

	override val datePattern = "dd/MM/yyyy"
	override val tagPrefix = "series-genre/"
	override val listUrl = "series/"

	override suspend fun getChapters(manga: Manga, doc: Document): List<MangaChapter> {
		return fetchAllChapters(manga)
	}

	override suspend fun loadChapters(mangaUrl: String, document: Document): List<MangaChapter> {
		return fetchAllChapters(
			Manga(
				id = generateUid(mangaUrl),
				url = mangaUrl,
				publicUrl = mangaUrl.toAbsoluteUrl(domain),
				title = "",
				altTitles = emptySet(),
				authors = emptySet(),
				tags = emptySet(),
				rating = RATING_UNKNOWN,
				state = MangaState.ONGOING,
				coverUrl = null,
				contentRating = ContentRating.SAFE,
				source = source,
			),
		)
	}

	private suspend fun fetchAllChapters(manga: Manga): List<MangaChapter> = coroutineScope {
		val baseUrl = "${manga.url.toAbsoluteUrl(domain).removeSuffix('/')}/ajax/chapters/?t="
		val dateFormat = SimpleDateFormat(datePattern, sourceLocale)

		// Fetch first page
		val firstPageDoc = webClient.httpPost(baseUrl + "1", emptyMap()).parseHtml()
		val totalPages = extractTotalPages(firstPageDoc)
		val firstPageChapters = firstPageDoc.select(selectChapter).map { parseChapterElement(it, dateFormat) }

		if (totalPages <= 1) {
			return@coroutineScope firstPageChapters.mapChapters(reversed = true) { index, chapter ->
				chapter.copy(number = (firstPageChapters.size - index).toFloat())
			}
		}

		// Fetch remaining pages concurrently
		val remainingPagesChapters = (2..totalPages).chunked(10).flatMap { batch ->
			batch.map { page ->
				async {
					try {
						val doc = webClient.httpPost(baseUrl + page, emptyMap()).parseHtml()
						doc.select(selectChapter).map {
							parseChapterElement(it, dateFormat)
						}
					} catch (e: Exception) {
						emptyList()
					}
				}
			}.awaitAll().flatten()
		}

		val allChapters = firstPageChapters + remainingPagesChapters
		allChapters.mapChapters(reversed = true) { index, chapter ->
			chapter.copy(number = (allChapters.size - index).toFloat())
		}

	}

	private fun extractTotalPages(doc: Document): Int {
		val pagination = doc.selectFirst(".pagination") ?: return 1

		return pagination.select("a[data-page]").mapNotNull { it.attr("data-page").toIntOrNull() }.maxOrNull() ?: 1
	}

	private fun parseChapterElement(li: Element, dateFormat: SimpleDateFormat): MangaChapter {
		val a = li.selectFirstOrThrow("a")
		val href = a.attrAsRelativeUrl("href")

		return MangaChapter(
			id = generateUid(href),
			title = a.ownText().ifEmpty { a.text() },
			number = 0f, // Will be set later
			volume = 0,
			url = href + stylePage,
			uploadDate = parseChapterDate(dateFormat, li.selectFirst(selectDate)?.textOrNull()),
			source = source,
			scanlator = null,
			branch = null,
		)
	}
}

