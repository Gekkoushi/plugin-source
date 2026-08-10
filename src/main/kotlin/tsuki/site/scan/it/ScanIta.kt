package tsuki.site.scan.it

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.jsoup.nodes.Document
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.Manga
import tsuki.model.MangaChapter
import tsuki.model.MangaParserSource
import tsuki.model.RATING_UNKNOWN
import tsuki.site.scan.ScanParser
import tsuki.util.*
import java.text.SimpleDateFormat

@MangaSourceParser("SCANITA", "ScanIta.org", "it")
internal class ScanIta(context: MangaLoaderContext) :
	ScanParser(context, MangaParserSource.SCANITA, "scanita.org") {

	override suspend fun getDetails(manga: Manga): Manga = coroutineScope {
		val doc = webClient.httpGet(manga.url.toAbsoluteUrl(domain)).parseHtml()
		val tagMap = getOrCreateTagMap()
		val selectTag = doc.select(".card-series-detail .col-6:contains(Categorie) div")
		val tags = selectTag.mapNotNullToSet { tagMap[it.text()] }
		val chaptersDeferred = async { loadChapters(doc) }
		val author = doc.selectFirst(".card-series-detail .col-6:contains(Autore) div")?.textOrNull()
		manga.copy(
			rating = doc.selectFirst(".card-series-detail .rate-value span")?.ownText()?.toFloatOrNull()?.div(5f)
				?: RATING_UNKNOWN,
			tags = tags,
			authors = setOfNotNull(author),
			altTitles = setOfNotNull(doc.selectFirst(".card div.col-12.mb-4 h2")?.textOrNull()),
			description = doc.selectFirst(".card div.col-12.mb-4 p")?.html(),
			chapters = chaptersDeferred.await(),
		)
	}

	private suspend fun loadChapters(document: Document): List<MangaChapter> {
		val id = document.selectFirstOrThrow(".container-fluid button.w-100").attr("data-path")
			.substringAfter("/manga/").substringBefore("/books")
		val url = "https://$domain/manga/$id/books"
		val doc = webClient.httpGet(url).parseHtml()
		val dateFormat = SimpleDateFormat("MM-dd-yyyy", sourceLocale)
		return doc.select(".chapters-list .col-chapter").mapChapters(reversed = true) { i, div ->
			val href = div.selectFirstOrThrow("a").attrAsRelativeUrl("href")
			MangaChapter(
				id = generateUid(href),
				title = div.selectFirstOrThrow("h5").html().substringBefore("<div").substringAfter("</span>"),
				number = i + 1f,
				volume = 0,
				url = href,
				scanlator = null,
				uploadDate = dateFormat.parseSafe(doc.selectFirstOrThrow("h5 div").text()),
				branch = null,
				source = source,
			)
		}
	}
}
