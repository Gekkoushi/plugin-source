package tsuki.site.all

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.config.ConfigKey
import tsuki.core.SinglePageMangaParser
import tsuki.model.Manga
import tsuki.model.MangaChapter
import tsuki.model.MangaListFilter
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaListFilterOptions
import tsuki.model.MangaPage
import tsuki.model.MangaParserSource
import tsuki.model.RATING_UNKNOWN
import tsuki.model.SortOrder
import tsuki.util.generateUid
import tsuki.util.parseHtml
import tsuki.util.parseSafe
import tsuki.util.selectFirstOrThrow
import tsuki.util.urlBuilder
import java.text.SimpleDateFormat
import java.util.EnumSet
import java.util.Locale

@MangaSourceParser("HOLOEARTH", "HoloEarth")
internal class HoloEarthParser(context: MangaLoaderContext) :
    SinglePageMangaParser(context, MangaParserSource.HOLOEARTH) {

	override val configKeyDomain: ConfigKey.Domain
		get() = ConfigKey.Domain("holoearth.com")

	override fun onCreateConfig(keys: MutableCollection<ConfigKey<*>>) {
		super.onCreateConfig(keys)
		keys.add(userAgentKey)
	}

	override val availableSortOrders: Set<SortOrder> = EnumSet.of(
		SortOrder.NEWEST,
	)

	override val filterCapabilities: MangaListFilterCapabilities
        get() = MangaListFilterCapabilities(
            isSearchSupported = false,
        )

	override suspend fun getFilterOptions() = MangaListFilterOptions(
		availableLocales = setOf(
            Locale("en"),
            Locale.JAPANESE,
            Locale("id"),
        ),
	)

	override suspend fun getList(order: SortOrder, filter: MangaListFilter): List<Manga> {
		val url = urlBuilder().apply {
			when (filter.locale) {
				Locale("en") -> addPathSegment("en")
				Locale("id") -> addPathSegment("id")
				else -> {}
			}

			addPathSegments("/alt/holonometria/manga")
		}.build()

		val doc = webClient.httpGet(url).parseHtml()
		val root = doc.body().selectFirstOrThrow(".manga__list")
		val mangaList = root.select("li .manga__item-inner")

		if (mangaList.isEmpty()) return emptyList()

		return mangaList.mapNotNull { li ->
			val coverUrl = li.getElementsByTag("img").attr("src")
			val title = li.getElementsByClass("manga__title").text()
			val altTitle = li.getElementsByClass("manga__copy").text()
			val description = li.getElementsByClass("manga__caption").text()
			val url = li.getElementsByTag("a").attr("href")

			Manga(
				id = generateUid(url),
				title = title,
				altTitles = setOf(altTitle),
				url = url,
				publicUrl = url,
				rating = RATING_UNKNOWN,
				contentRating = null,
				coverUrl = coverUrl,
				tags = emptySet(),
				state = null,
				authors = emptySet(),
				source = source,
				description = description,
			)
		}
	}

	override suspend fun getDetails(manga: Manga): Manga {
		val doc = webClient.httpGet(manga.url).parseHtml()
		val dateFormat = SimpleDateFormat("yyyy.MM.dd", Locale.US)
		val root = doc.body().selectFirstOrThrow(".manga-detail__wrapper")
		val coverUrl = root.selectFirstOrThrow(".manga-detail__thumb img").attr("src")
		val chapters = root.select(".manga-detail__list-item")
		val mangaChapters = chapters.mapIndexed { index, li ->
			val url = li.selectFirstOrThrow(".manga-detail__list-link").attr("href")
			val title = li.selectFirstOrThrow(".manga-detail__list-title").text()
			val dateStr = li.selectFirstOrThrow(".manga-detail__list-date").text()
			val uploadDate = dateFormat.parseSafe(dateStr)
			val scanlator = root.selectFirst(".manga-detail__person")?.text()

			MangaChapter(
				id = generateUid(url),
				title = title,
				number = index + 1f,
				volume = 0,
				url = url,
				scanlator = scanlator,
				uploadDate = uploadDate,
				branch = null,
				source = source,
			)
		}

		return manga.copy(
			coverUrl = coverUrl,
			chapters = mangaChapters,
		)
	}


	override suspend fun getPages(chapter: MangaChapter): List<MangaPage> {
		val doc = webClient.httpGet(chapter.url).parseHtml()
		val imageList = doc.body().selectFirstOrThrow(".manga-detail__swiper-wrapper")
		val images = imageList.select(".manga-detail__swiper-slide").reversed()

		return images.mapNotNull { page ->
			val img = page.selectFirst("img") ?: return@mapNotNull null
			val src = img.attr("src")
			MangaPage(
				id = generateUid(src),
				url = src,
				preview = src,
				source = source,
			)
		}
	}
}
