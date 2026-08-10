package tsuki.site.ja

import okhttp3.Headers
import org.json.JSONArray
import org.json.JSONObject
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.config.ConfigKey
import tsuki.core.PagedMangaParser
import tsuki.model.ContentRating
import tsuki.model.ContentType
import tsuki.model.Manga
import tsuki.model.MangaChapter
import tsuki.model.MangaListFilter
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaListFilterOptions
import tsuki.model.MangaPage
import tsuki.model.MangaParserSource
import tsuki.model.MangaState
import tsuki.model.MangaTag
import tsuki.model.RATING_UNKNOWN
import tsuki.model.SortOrder
import tsuki.network.CommonHeaders
import tsuki.network.UserAgents
import tsuki.util.generateUid
import tsuki.util.json.mapJSONNotNull
import tsuki.util.json.mapJSONToSet
import tsuki.util.mapChapters
import tsuki.util.parseJson
import tsuki.util.parseJsonArray
import tsuki.util.parseSafe
import tsuki.util.urlBuilder
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.EnumSet
import java.util.Locale
import java.util.TimeZone

@MangaSourceParser("KLZ9", "Klz9", "ja", ContentType.HENTAI)
internal class Klz9(context: MangaLoaderContext) :
    PagedMangaParser(context, MangaParserSource.KLZ9, 36) {

    override val configKeyDomain = ConfigKey.Domain("klz9.com")

    override val availableSortOrders: Set<SortOrder> = EnumSet.of(
		SortOrder.ALPHABETICAL,
        SortOrder.UPDATED,
        SortOrder.POPULARITY,
        SortOrder.NEWEST,
    )

    override val filterCapabilities = MangaListFilterCapabilities(
        isSearchSupported = true,
        isSearchWithFiltersSupported = true,
        isMultipleTagsSupported = true,
    )

    override suspend fun getFilterOptions(): MangaListFilterOptions {
        return MangaListFilterOptions(
            availableTags = fetchTags(),
            availableStates = EnumSet.of(
                MangaState.ONGOING,
                MangaState.FINISHED,
            )
        )
    }

    override suspend fun getListPage(page: Int, order: SortOrder, filter: MangaListFilter): List<Manga> {
		val builder = urlBuilder()
			.addPathSegment("api").addPathSegment("manga")
			.addPathSegment("list")

        builder.addQueryParameter("page", page.toString())
        builder.addQueryParameter("limit", pageSize.toString())

        if (!filter.query.isNullOrEmpty()) {
            builder.addQueryParameter("search", filter.query)
        }

        if (filter.tags.isNotEmpty()) {
            builder.addQueryParameter(
				"genre",
				filter.tags.joinToString(",") { it.key }
			)
        }

        when (order) {
            SortOrder.POPULARITY -> {
                builder.addQueryParameter("sort", "views")
                builder.addQueryParameter("order", "desc")
            }
            SortOrder.UPDATED -> {
                builder.addQueryParameter("sort", "updated")
                builder.addQueryParameter("order", "desc")
            }
            SortOrder.NEWEST -> {
                builder.addQueryParameter("sort", "created")
                builder.addQueryParameter("order", "desc")
            }
            SortOrder.ALPHABETICAL -> {
                builder.addQueryParameter("sort", "name")
                builder.addQueryParameter("order", "asc")
            }
            else -> {
                builder.addQueryParameter("sort", "updated")
                builder.addQueryParameter("order", "desc")
            }
        }

        val json = webClient.httpGet(builder.build(), generateHeaders()).parseJson()
		return json.getJSONArray("items").mapJSONNotNull { item ->
			val url = item.getString("slug") ?: return@mapJSONNotNull null
            Manga(
                id = generateUid(url),
                url = url,
                publicUrl = "https://$domain/${url}.html",
                coverUrl = item.optString("cover", ""),
                title = item.getString("name"),
                altTitles = emptySet(),
                rating = RATING_UNKNOWN,
                tags = emptySet(),
                authors = emptySet(),
                state = when (item.optInt("m_status", 0)) {
                    1 -> MangaState.ONGOING
                    2 -> MangaState.FINISHED
                    else -> null
                },
                contentRating = ContentRating.ADULT,
                source = source,
            )
		}
    }

    override suspend fun getDetails(manga: Manga): Manga {
        val url = "https://$domain/api/manga/slug/${manga.url}"
        val json = webClient.httpGet(url, generateHeaders()).parseJson()

        val authors = json.optString("authors", "")
			.split(",").map { it.trim() }
			.filter { it.isNotEmpty() }.toSet()

        val genres = json.optString("genres", "")
			.split(",").map { it.trim() }
			.filter { it.isNotEmpty() }.toSet()

        val desc = json.optString("description", "")
            .replace(Regex("<[^>]*>"), "")
            .replace("&amp;", "&")

        val chapters = parseChapters(json)

        return manga.copy(
            description = desc,
            authors = authors,
            tags = genres.map { MangaTag(title = it, key = it, source = source) }.toSet(),
            coverUrl = json.optString("cover", manga.coverUrl),
            state = when (json.optInt("m_status", 0)) {
                1 -> MangaState.ONGOING
                2 -> MangaState.FINISHED
                else -> null
            },
            chapters = chapters,
        )
    }

    private fun parseChapters(json: JSONObject): List<MangaChapter> {
		val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        	dateFormat.timeZone = TimeZone.getTimeZone("UTC")
		return json.getJSONArray("chapters").mapChapters { i, item ->
			val id = item.getLong("id")
			val chapterNum = item.optDouble("chapter", (i + 1).toDouble()).toFloat()
			val title = item.optString("name").takeIf { !it.isNullOrEmpty() && it != "null" }
				?: "Chapter $chapterNum"
            MangaChapter(
                id = id,
                title = title,
                number = chapterNum,
                volume = 0,
                url = "chapter/$id",
                scanlator = null,
                uploadDate = dateFormat.parseSafe(item.optString("last_update")),
                branch = null,
                source = source,
            )
		}.sortedBy { it.number }
    }

	override suspend fun getPages(chapter: MangaChapter): List<MangaPage> {
		val url = "https://$domain/api/${chapter.url}"
		val json = webClient.httpGet(url, generateHeaders()).parseJson()
		val content = json.optString("content", "")
		return parseContentUrls(content).mapNotNull { originalUrl ->
			val finalUrl = filterUrl(originalUrl) ?: return@mapNotNull null
            MangaPage(
                id = generateUid(finalUrl),
                url = finalUrl,
                preview = null,
                source = source
            )
		}
	}

	private suspend fun fetchTags(): Set<MangaTag> {
		val url = "https://$domain/api/genres"
		val response = webClient.httpGet(url, generateHeaders()).parseJsonArray()
		return response.mapJSONToSet {
			val name = it.getString("name")
            MangaTag(
                title = name,
                key = name,
                source = source,
            )
		}
	}

	private fun parseContentUrls(content: String): List<String> {
		return if (content.trim().startsWith("[")) {
			try {
				val jsonArray = JSONArray(content)
				val list = mutableListOf<String>()
				for (i in 0 until jsonArray.length()) {
					list.add(jsonArray.getString(i))
				}
				list
			} catch (_: Exception) {
				emptyList()
			}
		} else {
			content.split(Regex("[\r\n]+")).map { it.trim() }.filter { it.startsWith("http") }
		}
	}

	private fun filterUrl(url: String): String? {
		val blacklisted = setOf(
			"https://1.bp.blogspot.com/-ZMyVQcnjYyE/W2cRdXQb15I/AAAAAAACDnk/8X1Hm7wmhz4hLvpIzTNBHQnhuKu05Qb0gCHMYCw/s0/LHScan.png",
			"https://s4.imfaclub.com/images/20190814/Credit_LHScan_5d52edc2409e7.jpg",
			"https://s4.imfaclub.com/images/20200112/5e1ad960d67b2_5e1ad962338c7.jpg"
		)

		if (blacklisted.contains(url)) return null

		var newUrl = url.replace("http://", "https://")
		newUrl = newUrl.replace("https://imfaclub.com", "https://h1.klimv1.xyz")
		newUrl = newUrl.replace("https://s2.imfaclub.com", "https://h2.klimv1.xyz")
		newUrl = newUrl.replace("https://s4.imfaclub.com", "https://h4.klimv1.xyz")
		newUrl = newUrl.replace("https://ihlv1.xyz", "https://h1.klimv1.xyz")
		newUrl = newUrl.replace("https://s2.ihlv1.xyz", "https://h2.klimv1.xyz")
		newUrl = newUrl.replace("https://s4.ihlv1.xyz", "https://h4.klimv1.xyz")
		newUrl = newUrl.replace("https://h1.klimv1.xyz", "https://j1.jfimv2.xyz")
		newUrl = newUrl.replace("https://h2.klimv1.xyz", "https://j2.jfimv2.xyz")
		newUrl = newUrl.replace("https://h4.klimv1.xyz", "https://j4.jfimv2.xyz")

		return newUrl
	}

	private fun generateHeaders(): Headers {
        val ts = (System.currentTimeMillis() / 1000).toString()
        val s = "$ts.$SECRET_KEY"
        val sig = MessageDigest.getInstance("SHA-256").digest(s.toByteArray())
            .joinToString("") { "%02x".format(it) }

        return Headers.Builder()
            .add(CommonHeaders.X_CLIENT_TS, ts)
            .add(CommonHeaders.X_CLIENT_SIG, sig)
            .add(CommonHeaders.USER_AGENT, UserAgents.CHROME_DESKTOP)
            .build()
    }

	private companion object {
		const val SECRET_KEY = "KL9K40zaSyC9K40vOMLLbEcepIFBhUKXwELqxlwTEF"
	}
}
