package tsuki.site.mangareader.es

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.Manga
import tsuki.model.MangaListFilter
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.model.MangaState
import tsuki.model.SortOrder
import tsuki.model.ContentType
import tsuki.site.mangareader.MangaReaderParser
import tsuki.util.oneOrThrowIfMany
import tsuki.util.parseHtml
import tsuki.util.urlEncoded
import java.util.Locale

@MangaSourceParser("LEGIONSCANS", "CerberusSeries", "es")
internal class CerberuSeries(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.LEGIONSCANS, "legionscans.com", pageSize = 20, searchPageSize = 10) {

	override val listUrl = "/wp/manga"
	override val sourceLocale: Locale = Locale.ENGLISH

	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)

	// Site is served at /wp/ subpath, so search URLs need that prefix too.
	override suspend fun getListPage(page: Int, order: SortOrder, filter: MangaListFilter): List<Manga> {
		val url = buildString {
			append("https://")
			append(domain)

			when {
				!filter.query.isNullOrEmpty() -> {
					append("/wp/page/")
					append(page.toString())
					append("/?s=")
					append(filter.query.urlEncoded())
				}

				else -> {
					append(listUrl)
					append("/?order=")
					append(
						when (order) {
							SortOrder.ALPHABETICAL -> "title"
							SortOrder.ALPHABETICAL_DESC -> "titlereverse"
							SortOrder.NEWEST -> "latest"
							SortOrder.POPULARITY -> "popular"
							SortOrder.UPDATED -> "update"
							else -> ""
						},
					)

					filter.tags.forEach {
						append("&")
						append("genre[]".urlEncoded())
						append("=")
						append(it.key)
					}

					if (filter.states.isNotEmpty()) {
						filter.states.oneOrThrowIfMany()?.let {
							append("&status=")
							when (it) {
								MangaState.ONGOING -> append("ongoing")
								MangaState.FINISHED -> append("completed")
								MangaState.PAUSED -> append("hiatus")
								else -> append("")
							}
						}
					}

					filter.types.oneOrThrowIfMany()?.let {
						append("&type=")
						append(
							when (it) {
								ContentType.MANGA -> "manga"
								ContentType.MANHWA -> "manhwa"
								ContentType.MANHUA -> "manhua"
								ContentType.COMICS -> "comic"
								ContentType.NOVEL -> "novel"
								else -> ""
							},
						)
					}

					append("&page=")
					append(page.toString())
				}
			}
		}
		return parseMangaList(webClient.httpGet(url).parseHtml())
	}
}
