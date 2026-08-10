package tsuki.site.wpcomics.ja

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.exception.NotFoundException
import tsuki.model.*
import tsuki.site.wpcomics.WpComicsParser
import tsuki.util.*

// Need to use 0ms.dev Proxy

@MangaSourceParser("MANGARAW", "MangaRaw", "ja")
internal class MangaRaw(context: MangaLoaderContext) :
	WpComicsParser(context, MangaParserSource.MANGARAW, "mangaraw.best") {
	override val listUrl = "/search/manga"

	override suspend fun getListPage(page: Int, order: SortOrder, filter: MangaListFilter): List<Manga> {
		val response = when {
			!filter.query.isNullOrEmpty() -> {
				val url = buildString {
					append("https://")
					append(domain)
					append(listUrl)
					append("?keyword=")
					append(filter.query.urlEncoded())
					append("&page=")
					append(page.toString())
				}

				val result = runCatchingCancellable { webClient.httpGet(url) }
				val exception = result.exceptionOrNull()
				if (exception is NotFoundException) {
					return emptyList()
				}
				result.getOrThrow()
			}

			else -> {
				val url = buildString {
					append("https://")
					append(domain)
					append(listUrl)
					append("?sort=")
					append(
						when (order) {
							SortOrder.UPDATED -> 0
							SortOrder.POPULARITY -> 10
							SortOrder.NEWEST -> 15
							SortOrder.RATING -> 20
							else -> throw IllegalArgumentException("Sort order ${order.name} not supported")
						},
					)
					if (filter.tags.isNotEmpty()) {
						append("&genre=")
						filter.tags.oneOrThrowIfMany()?.let {
							append(it.key)
						}
					}
					filter.states.oneOrThrowIfMany()?.let {
						append("&status=")
						append(
							when (it) {
								MangaState.ONGOING -> "1"
								MangaState.FINISHED -> "2"
								else -> "-1"
							},
						)
					}
					append("&page=")
					append(page.toString())
				}

				webClient.httpGet(url)
			}
		}
		val tagMap = getOrCreateTagMap()
		return parseMangaList(response.parseHtml(), tagMap)
	}
}
