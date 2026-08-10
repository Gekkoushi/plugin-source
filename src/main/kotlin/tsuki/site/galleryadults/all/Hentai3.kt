package tsuki.site.galleryadults.all

import org.jsoup.internal.StringUtil
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.*
import tsuki.site.galleryadults.GalleryAdultsParser
import tsuki.util.*
import java.util.*

@MangaSourceParser("HENTAI3", "3Hentai", type = ContentType.HENTAI)
internal class Hentai3(context: MangaLoaderContext) :
	GalleryAdultsParser(context, MangaParserSource.HENTAI3, "3hentai.net", pageSize = 30) {

	override val selectGallery = ".doujin "
	override val selectGalleryLink = "a"
	override val selectGalleryTitle = ".title"
	override val pathTagUrl = "/tags-popular/"
	override val selectTags = "div.tag-listing-container"
	override val selectTag = "div.tag-container:contains(Tags)"
	override val selectAuthor = "div.tag-container:contains(Artists) .filter-elem a"
	override val selectLanguageChapter = "div.tag-container:contains(Languages) a"
	override val selectUrlChapter = "#main-cover a"
	override val idImg = ".js-main-img"
	override val selectTotalPage = ".js-num-pages, .num-pages"

	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isMultipleTagsSupported = true,
		)

	override val availableSortOrders: Set<SortOrder> = EnumSet.of(SortOrder.UPDATED, SortOrder.POPULARITY)

	override suspend fun getFilterOptions() = super.getFilterOptions().copy(
		availableLocales = setOf(
			Locale.ENGLISH,
			Locale.FRENCH,
			Locale.JAPANESE,
			Locale("es"),
			Locale("ru"),
			Locale.ITALIAN,
			Locale("pt"),
		),
	)

	override suspend fun getListPage(page: Int, order: SortOrder, filter: MangaListFilter): List<Manga> {
		val url = buildString {
			append("https://")
			append(domain)
			when {

				!filter.query.isNullOrEmpty() -> {
					append("/search?q=")
					append(filter.query.urlEncoded())
					append("&page=")
					append(page.toString())
				}

				else -> {
					val locale = filter.locale
					if (filter.tags.size > 1 || (filter.tags.isNotEmpty() && locale != null)) {
						append("/search?q=")
						append(buildQuery(filter.tags, locale))
						if (order == SortOrder.POPULARITY) {
							append("&sort=popular")
						}
						append("&page=")
						append(page.toString())
					} else if (locale != null) {
						append("/language/")
						append(locale.toLanguagePath())
						append("/")
						append(page.toString())
						if (order == SortOrder.POPULARITY) {
							append("?sort=popular")
						}
					} else if (filter.tags.isNotEmpty()) {
						filter.tags.oneOrThrowIfMany()?.let {
							append("/tags/")
							append(it.key)
						}
						append("/")
						append(page.toString())
						if (order == SortOrder.POPULARITY) {
							append("?sort=popular")
						}
					} else {
						append("/")
						append(page)
					}
				}
			}
		}
		return parseMangaList(webClient.httpGet(url).parseHtml())
	}

	override suspend fun getPageUrl(page: MangaPage): String {
		val doc = webClient.httpGet(page.url.toAbsoluteUrl(domain)).parseHtml()
		return doc.selectFirstOrThrow(idImg).requireSrc()
	}

	private fun buildQuery(tags: Collection<MangaTag>, language: Locale?): String {
		val joiner = StringUtil.StringJoiner(" ")
		tags.forEach { tag ->
			joiner.add("tag:\"")
			joiner.append(tag.key)
			joiner.append("\"")
		}
		language?.let { lc ->
			joiner.add("language:\"")
			joiner.append(lc.toLanguagePath())
			joiner.append("\"")
		}
		return joiner.complete()
	}
}
