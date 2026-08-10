package tsuki.site.galleryadults.all

import org.jsoup.internal.StringUtil
import org.jsoup.nodes.Element
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.*
import tsuki.site.galleryadults.GalleryAdultsParser
import tsuki.util.*
import java.util.*

@MangaSourceParser("HENTAIFOX", "HentaiFox", type = ContentType.HENTAI)
internal class HentaiFox(context: MangaLoaderContext) :
	GalleryAdultsParser(context, MangaParserSource.HENTAIFOX, "hentaifox.com") {
	override val selectGallery = ".lc_galleries .thumb, .related_galleries .thumb"
	override val pathTagUrl = "/tags/popular/pag/"
	override val selectTags = ".list_tags"
	override val selectTag = "ul.tags"
	override val selectLanguageChapter = "ul.languages a.tag_btn"

	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isMultipleTagsSupported = true,
		)

	override val availableSortOrders: Set<SortOrder> = EnumSet.of(SortOrder.UPDATED, SortOrder.POPULARITY)

	override suspend fun getListPage(page: Int, order: SortOrder, filter: MangaListFilter): List<Manga> {
		val url = buildString {
			append("https://")
			append(domain)
			when {
				!filter.query.isNullOrEmpty() -> {
					append("/search/?q=")
					append(filter.query.urlEncoded())
					if (page > 1) {
						append("&page=")
						append(page.toString())
					}
				}

				else -> {
					val locale = filter.locale
					if (filter.tags.size > 1 || (filter.tags.isNotEmpty() && locale != null)) {
						append("/search/?q=")
						append(buildQuery(filter.tags, locale))
						if (page > 1) {
							append("&page=")
							append(page.toString())
						}

						if (order == SortOrder.POPULARITY) {
							append("&sort=popular")
						}
					} else if (filter.tags.isNotEmpty()) {
						filter.tags.oneOrThrowIfMany()?.let {
							append("/tag/")
							append(it.key)
						}
						append("/")
						if (order == SortOrder.POPULARITY) {
							append("popular/")
						}

						if (page > 1) {
							append("/pag/")
							append(page.toString())
							append("/")
						}
					} else if (locale != null) {
						append("/language/")
						append(locale.toLanguagePath())
						append("/")
						if (order == SortOrder.POPULARITY) {
							append("popular/")
						}

						if (page > 1) {
							append("/pag/")
							append(page.toString())
							append("/")
						}
					} else {
						if (page > 2) {
							append("/pag/")
							append(page.toString())
							append("/")
						} else if (page > 1) {
							append("/page/")
							append(page.toString())
							append("/")
						}
					}
				}
			}
		}

		return parseMangaList(webClient.httpGet(url).parseHtml())
	}

	override fun Element.parseTags() = select("a").mapToSet {
		val key = it.attr("href").removeSuffix('/').substringAfterLast('/')
		val name = it.selectFirst(".list_tag")?.text() ?: it.html().substringBefore("<")
		MangaTag(
			key = key,
			title = name,
			source = source,
		)
	}

	private fun buildQuery(tags: Collection<MangaTag>, language: Locale?): String {
		val joiner = StringUtil.StringJoiner(" ")
		tags.forEach { tag ->
			joiner.add(tag.key)
		}
		language?.let { lc ->
			joiner.add(lc.toLanguagePath())
		}
		return joiner.complete()
	}
}
