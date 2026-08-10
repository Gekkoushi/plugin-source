package tsuki.site.madara.es

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.jsoup.nodes.Document
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.exception.ParseException
import tsuki.model.*
import tsuki.site.madara.MadaraParser
import tsuki.util.*
import java.text.SimpleDateFormat

@MangaSourceParser("MANGASNOSEKAI", "MangasNoSekai", "es")
internal class MangasNoSekai(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGASNOSEKAI, "mangasnosekai.com") {

	override suspend fun getDetails(manga: Manga): Manga = coroutineScope {
		val fullUrl = manga.url.toAbsoluteUrl(domain)
		val doc = webClient.httpGet(fullUrl).parseHtml()
		val body = doc.body()
		val chaptersDeferred = async { loadChapters(manga.url, doc) }
		val author = doc.selectFirst("section#section-sinopsis div.d-flex:has(div:contains(Autor)) p a")?.textOrNull()
		manga.copy(
			tags = doc.body().select("#section-sinopsis a[href*=genre]").mapToSet { a ->
				MangaTag(
					key = a.attr("href").removeSuffix('/').substringAfterLast('/'),
					title = a.text().toTitleCase(),
					source = source,
				)
			},
			authors = setOfNotNull(author),
			description = body.selectFirst("#section-sinopsis p")?.text().orEmpty(),
			altTitles = setOfNotNull(
				doc.selectFirst("section#section-sinopsis div.d-flex:has(div:contains(Otros nombres)) p")
					?.textOrNull(),
			),
			state = body.selectFirst("section#section-sinopsis div.d-flex:has(div:contains(Estado)) p")
				?.let {
					when (it.text()) {
						in ongoing -> MangaState.ONGOING
						in finished -> MangaState.FINISHED
						in abandoned -> MangaState.ABANDONED
						in paused -> MangaState.PAUSED
						else -> null
					}
				},
			chapters = chaptersDeferred.await(),
		)
	}

	// todo take other pages
	override suspend fun loadChapters(mangaUrl: String, document: Document): List<MangaChapter> {
		val dateFormat = SimpleDateFormat(datePattern, sourceLocale)
		return document.select("div.container-capitulos div.contenedor-capitulo-miniatura")
			.mapChapters(reversed = true) { i, div ->
				val a = div.selectFirst("a")
				val href = a?.attrAsRelativeUrlOrNull("href") ?: div.parseFailed("Link is missing")
				val link = href + stylePage
				val dateText = div.selectFirst("a div.chapter-text")?.text()
				val name = div.selectFirst("a div.text-sm")?.text() ?: a.ownText()
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

	override suspend fun getPages(chapter: MangaChapter): List<MangaPage> {
		val fullUrl = chapter.url.toAbsoluteUrl(domain)
		val doc = webClient.httpGet(fullUrl).parseHtml()
		val root = doc.body().selectFirst("div.reading-content")
			?: throw ParseException("No image found, try to log in", fullUrl)
		return root.select(selectPage).map { div ->
			val img = div.selectFirstOrThrow("img")
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
