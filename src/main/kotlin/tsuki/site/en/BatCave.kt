package tsuki.site.en

import org.json.JSONObject
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.config.ConfigKey
import tsuki.core.PagedMangaParser
import tsuki.exception.ParseException
import tsuki.model.*
import tsuki.network.CommonHeaders
import tsuki.util.*
import tsuki.util.json.getFloatOrDefault
import tsuki.util.json.getStringOrNull
import tsuki.util.suspendlazy.getOrNull
import tsuki.util.suspendlazy.suspendLazy
import java.text.SimpleDateFormat
import java.util.*

@MangaSourceParser("BATCAVE", "BatCave", "en", ContentType.COMICS)
internal class BatCave(context: MangaLoaderContext) :
	PagedMangaParser(context, MangaParserSource.BATCAVE, 20) {

	override val configKeyDomain = ConfigKey.Domain("batcave.biz")

    override fun getRequestHeaders() = super.getRequestHeaders().newBuilder()
        .add(CommonHeaders.REFERER, "https://$domain/")
        .build()

	private val availableTags = suspendLazy(initializer = ::fetchTags)

	override fun onCreateConfig(keys: MutableCollection<ConfigKey<*>>) {
		super.onCreateConfig(keys)
		keys.add(userAgentKey)
	}

	override val availableSortOrders: Set<SortOrder> = EnumSet.of(SortOrder.UPDATED)

	override val filterCapabilities: MangaListFilterCapabilities
		get() = MangaListFilterCapabilities(
			isSearchSupported = true,
			isMultipleTagsSupported = true,
			isYearRangeSupported = true,
		)

	override suspend fun getFilterOptions() = MangaListFilterOptions(
		availableTags = availableTags.get(),
	)

	override suspend fun getListPage(page: Int, order: SortOrder, filter: MangaListFilter): List<Manga> {
		val urlBuilder = StringBuilder()
		when {
			!filter.query.isNullOrEmpty() -> {
				val encodedQuery = filter.query.splitByWhitespace().joinToString(separator = "%20") { part ->
					part.urlEncoded()
				}
				urlBuilder.append("/search/")
				urlBuilder.append(encodedQuery)
				if (page > 1) {
					urlBuilder.append("/page/$page/")
				}
			}

			else -> {
				urlBuilder.append("/ComicList")
				if (filter.yearFrom != YEAR_UNKNOWN) {
					urlBuilder.append("/y[from]=${filter.yearFrom}")
				}
				if (filter.yearTo != YEAR_UNKNOWN) {
					urlBuilder.append("/y[to]=${filter.yearTo}")
				}
				if (filter.tags.isNotEmpty()) {
					urlBuilder.append("/g=")
					urlBuilder.append(filter.tags.joinToString(",") { it.key })
				}
				urlBuilder.append("/sort")
				if (page > 1) {
					urlBuilder.append("/page/$page/")
				}
			}
		}

		val fullUrl = urlBuilder.toString().toAbsoluteUrl(domain)
		val doc = webClient.httpGet(fullUrl).parseHtml()
		return doc.select("div.readed.d-flex.short").map { item ->
			val a = item.selectFirstOrThrow("a.readed__img.img-fit-cover.anim")
			val titleElement = item.selectFirstOrThrow("h2.readed__title a")
			val img = item.selectFirst("img[data-src]")
			val href = a.attrAsRelativeUrl("href")
			Manga(
				id = generateUid(href),
				url = href,
				publicUrl = a.attr("href"),
				title = titleElement.text(),
				altTitles = emptySet(),
				authors = emptySet(),
				description = null,
				tags = emptySet(),
				rating = RATING_UNKNOWN,
				state = null,
				coverUrl = img?.attrAsAbsoluteUrlOrNull("data-src"),
				contentRating = if (isNsfwSource) ContentRating.ADULT else null,
				source = source,
			)
		}
	}

	override suspend fun getDetails(manga: Manga): Manga {
		val doc = webClient.httpGet(manga.url.toAbsoluteUrl(domain)).parseHtml()

		val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.US)

		val scriptData = doc.selectFirst("script:containsData(__DATA__)")?.data()
			?.let { data ->
				val jsonStart = data.indexOf("window.__DATA__ = ") + "window.__DATA__ = ".length
				val jsonEnd = data.indexOf("};", startIndex = jsonStart)
				if (jsonEnd != -1) {
					// substring, include "}" symbol
					data.substring(jsonStart, jsonEnd + 1)
				} else {
					null
				}
			} ?: doc.parseFailed("Script data not found")

		val jsonData = JSONObject(scriptData)
		val newsId = jsonData.getLong("news_id")
		val chaptersJson = jsonData.getJSONArray("chapters")

		val chapters = List(chaptersJson.length()) { i ->
			val chapter = chaptersJson.getJSONObject(i)
			val chapterId = chapter.getLong("id")

			MangaChapter(
				id = generateUid("$newsId/$chapterId"),
				url = "/reader/$newsId/$chapterId",
				number = chapter.getFloatOrDefault("posi", 0f),
				title = chapter.getStringOrNull("title"),
				uploadDate = dateFormat.parseSafe(chapter.getStringOrNull("date")),
				source = source,
				scanlator = null,
				branch = null,
				volume = 0,
			)
		}.reversed()

		val author = doc.selectFirst("li:contains(Publisher:)")
			?.textOrNull()
			?.substringAfter("Publisher:")
			?.trim()
			?.nullIfEmpty()
		val state = when (
			doc.selectFirst("li:contains(Release type:)")?.text()?.substringAfter("Release type:")?.trim()
		) {
			"Ongoing" -> MangaState.ONGOING
			else -> MangaState.FINISHED
		}

		val tagLinks = doc.getElementsByAttributeValueContaining("href", "/genres/")
		val tags = if (tagLinks.isNotEmpty()) {
			availableTags.getOrNull()?.let { allTags ->
				tagLinks.mapNotNullToSet { a ->
					val tagName = a.text()
					allTags.find { it.title.equals(tagName, ignoreCase = true) }
				}
			}
		} else {
			null
		}

		return manga.copy(
			authors = setOfNotNull(author),
			state = state,
			chapters = chapters,
			description = doc.select("div.page__text.full-text.clearfix").textOrNull(),
			tags = tags ?: manga.tags,
		)
	}

	override suspend fun getPages(chapter: MangaChapter): List<MangaPage> {
		val doc = webClient.httpGet(chapter.url.toAbsoluteUrl(domain)).parseHtml()
		val data = doc.selectFirst("script:containsData(__DATA__)")?.data()
			?.substringAfter("=")
			?.trim()
			?.removeSuffix(";")
			?.substringAfter("\"images\":[")
			?.substringBefore("]")
			?.split(",")
			?.map { it.trim().removeSurrounding("\"").replace("\\", "") }
			?: throw ParseException("Image data not found", chapter.url)

		return data.map { imageUrl ->
			MangaPage(
				id = generateUid(imageUrl),
				url = imageUrl,
				preview = null,
				source = source,
			)
		}
	}

	private suspend fun fetchTags(): Set<MangaTag> {
		val doc = webClient.httpGet("https://$domain/comix/").parseHtml()
		val scriptData = doc.selectFirstOrThrow("script:containsData(__XFILTER__)").data()

		val genresJson = scriptData
			.substringAfter("\"g\":{")
			.substringBefore("}}}") + "}"

		val genresObj = JSONObject("{$genresJson}")
		val valuesArray = genresObj.getJSONArray("values")

		return Set(valuesArray.length()) { i ->
			val genre = valuesArray.getJSONObject(i)
			MangaTag(
				key = genre.getInt("id").toString(),
				title = genre.getString("value").toTitleCase(sourceLocale),
				source = source,
			)
		}
	}
}
