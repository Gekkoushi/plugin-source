package tsuki.site.madara.ja

import org.jsoup.nodes.Document
import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.*
import tsuki.site.madara.MadaraParser
import tsuki.util.*
import java.util.*

@Broken("Domain hijacked — now serves a JS redirect to spam/ads")
@MangaSourceParser("MANGAFENXI", "MangaFenxi", "ja")
internal class MangaFenxi(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGAFENXI, "mangafenxi.net", 40) {
	override val sourceLocale: Locale = Locale.ENGLISH
	override val tagPrefix = "genres/"

	override fun parseMangaList(doc: Document): List<Manga> {
		return doc.select("div.row.c-tabs-item__content").ifEmpty {
			doc.select("div.page-item-detail")
		}.map { div ->
			val href = div.selectFirst("a")?.attrAsRelativeUrlOrNull("href") ?: div.parseFailed("Link not found")
			val summary = div.selectFirst(".tab-summary") ?: div.selectFirst(".item-summary")
			val author = summary?.selectFirst(".mg_author")?.selectFirst("a")?.ownText()
			Manga(
				id = generateUid(href),
				url = href,
				publicUrl = href.toAbsoluteUrl(div.host ?: domain),
				coverUrl = div.selectFirst("img")?.attr("src")?.replace("-193x278", ""),
				title = (summary?.selectFirst("h3") ?: summary?.selectFirst("h4")
				?: div.selectFirst(".manga-name") ?: div.selectFirst(".post-title"))?.text().orEmpty(),
				altTitles = emptySet(),
				rating = div.selectFirst("span.total_votes")?.ownText()?.toFloatOrNull()?.div(5f) ?: -1f,
				tags = summary?.selectFirst(".mg_genres")?.select("a")?.mapNotNullToSet { a ->
					MangaTag(
						key = a.attr("href").removeSuffix('/').substringAfterLast('/'),
						title = a.text().ifEmpty { return@mapNotNullToSet null }.toTitleCase(),
						source = source,
					)
				}.orEmpty(),
				authors = setOfNotNull(author),
				state = when (
					summary?.selectFirst(".mg_status")
						?.selectFirst(".summary-content")
						?.ownText()
						.orEmpty()
				) {
					in ongoing -> MangaState.ONGOING
					in finished -> MangaState.FINISHED
					in abandoned -> MangaState.ABANDONED
					in paused -> MangaState.PAUSED
					in upcoming -> MangaState.UPCOMING
					else -> null
				},
				source = source,
				contentRating = if (isNsfwSource) ContentRating.ADULT else null,
			)
		}
	}
}
