package tsuki.site.en

import org.jsoup.nodes.Document
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
import tsuki.util.attrAsRelativeUrl
import tsuki.util.attrOrNull
import tsuki.util.generateUid
import tsuki.util.mapChapters
import tsuki.util.mapNotNullToSet
import tsuki.util.mapToSet
import tsuki.util.oneOrThrowIfMany
import tsuki.util.parseFailed
import tsuki.util.parseHtml
import tsuki.util.selectFirstOrThrow
import tsuki.util.src
import tsuki.util.textOrNull
import tsuki.util.toAbsoluteUrl
import tsuki.util.toTitleCase
import tsuki.util.urlEncoded
import java.text.SimpleDateFormat
import java.util.EnumSet

@MangaSourceParser("KDTSCANS", "KdtScans", "en")
internal class KdtScans(context: MangaLoaderContext) :
    PagedMangaParser(context, MangaParserSource.KDTSCANS, 20) {

    override val configKeyDomain = ConfigKey.Domain("www.silentquill.net")

    override val availableSortOrders: Set<SortOrder> = EnumSet.of(
        SortOrder.RELEVANCE,
        SortOrder.UPDATED,
        SortOrder.POPULARITY,
        SortOrder.NEWEST,
        SortOrder.ALPHABETICAL,
        SortOrder.ALPHABETICAL_DESC,
    )

    override val filterCapabilities = MangaListFilterCapabilities(
        isSearchSupported = true,
        isMultipleTagsSupported = true,
        isTagsExclusionSupported = true,
    )

    override suspend fun getFilterOptions(): MangaListFilterOptions {
        return MangaListFilterOptions(
            availableTags = fetchAvailableTags(),
            availableStates = EnumSet.of(
                MangaState.ONGOING,
                MangaState.FINISHED,
                MangaState.PAUSED,
            ),
            availableContentTypes = EnumSet.of(
                ContentType.MANGA,
                ContentType.MANHWA,
                ContentType.MANHUA,
                ContentType.COMICS,
                ContentType.NOVEL,
            ),
        )
    }

    override suspend fun getListPage(page: Int, order: SortOrder, filter: MangaListFilter): List<Manga> {
        val url = buildString {
            append("https://$domain/manga/?page=${page}")

            filter.query?.let {
                append("&s=${it.urlEncoded()}")
            }

            val sortValue = when (order) {
                SortOrder.UPDATED -> "update"
                SortOrder.POPULARITY -> "popular"
                SortOrder.NEWEST -> "latest"
                SortOrder.ALPHABETICAL -> "title"
                SortOrder.ALPHABETICAL_DESC -> "titlereverse"
                else -> "" // Default/Relevance
            }
            if (sortValue.isNotEmpty()) {
                append("&order=$sortValue")
            }

            filter.tags.forEach { tag ->
                append("&genre[]=${tag.key}")
            }

            filter.tagsExclude.forEach { tag ->
                append("&genre[]=-${tag.key}")
            }

            filter.states.oneOrThrowIfMany().let { state ->
                val stateValue = when (state) {
                    MangaState.ONGOING -> "ongoing"
                    MangaState.FINISHED -> "completed"
                    MangaState.PAUSED -> "hiatus"
                    else -> ""
                }
                if (stateValue.isNotEmpty()) {
                    append("&status=$stateValue")
                }
            }

            filter.types.oneOrThrowIfMany()?.let { type ->
                val typeValue = when (type) {
                    ContentType.MANGA -> "manga"
                    ContentType.MANHWA -> "manhwa"
                    ContentType.MANHUA -> "manhua"
                    ContentType.COMICS -> "comic"
                    ContentType.NOVEL -> "novel"
                    else -> ""
                }
                if (typeValue.isNotEmpty()) {
                    append("&type=$typeValue")
                }
            }
        }
        val doc = webClient.httpGet(url).parseHtml()
        return parseMangaList(doc)
    }

    private fun parseMangaList(doc: Document): List<Manga> {
        val elements = doc.select("div.listupd div.bs")

        if (elements.isEmpty()) {
            return emptyList()
        }

        return elements.map { div ->
            val a = div.selectFirstOrThrow("a")
            val href = a.attrAsRelativeUrl("href")
            val img = div.selectFirst("img")
            val title = a.attr("title").ifEmpty {
                div.selectFirst(".tt")?.text().orEmpty()
            }
            val rating = div.selectFirst(".numscore")?.text()?.toFloatOrNull()?.div(10f) ?: RATING_UNKNOWN

            Manga(
                id = generateUid(href),
                url = href,
                publicUrl = href.toAbsoluteUrl(domain),
                coverUrl = img?.src(),
                title = title,
                altTitles = emptySet(),
                rating = rating,
                tags = emptySet(),
                authors = emptySet(),
                state = parseStatus(div.selectFirst(".status")?.text().orEmpty()),
                source = source,
                contentRating = if (isNsfwSource) ContentRating.ADULT else null,
            )
        }
    }

    override suspend fun getDetails(manga: Manga): Manga {
        val doc = webClient.httpGet(manga.url.toAbsoluteUrl(domain)).parseHtml()
        val infoElement =
            doc.selectFirst(".main-info, .postbody") ?: doc.parseFailed("Cannot find manga details element")

        val statusText =
            infoElement.selectFirst(".tsinfo .imptdt:contains(Status) i, .infotable tr:contains(Status) td:last-child")
                ?.text()

        val chapters = doc.select("#chapterlist li").mapChapters(reversed = true) { i, li ->
            val a = li.selectFirstOrThrow("a")
            val href = a.attrAsRelativeUrl("href")
            MangaChapter(
                id = generateUid(href),
                url = href,
                title = a.selectFirst(".chapternum")?.text() ?: a.text(),
                number = i + 1f,
                uploadDate = parseChapterDate(li.selectFirst(".chapterdate")?.text()),
                source = source,
                volume = 0,
                scanlator = null,
                branch = null,
            )
        }

        val genres = infoElement.select(".mgen a, .seriestugenre a").mapToSet { a ->
            MangaTag(
                key = a.attr("href").substringAfterLast("/").removeSuffix("/"),
                title = a.text(),
                source = source,
            )
        }

        val typeTag = infoElement.selectFirst(".tsinfo .imptdt:contains(Type) a")?.text()?.let { typeText ->
            MangaTag(
                key = typeText.lowercase(),
                title = typeText.trim(),
                source = source,
            )
        }

        val allTags = genres.toMutableSet()
        typeTag?.let { allTags.add(it) }

        return manga.copy(
            title = infoElement.selectFirst("h1.entry-title")?.text() ?: manga.title,
            authors = infoElement.select(".tsinfo .imptdt:contains(Author) i, .infotable tr:contains(Author) td:last-child")
                .mapToSet { it.text() },
            description = infoElement.select(".desc, .entry-content[itemprop=description]")
                .joinToString("\n") { it.text() },
            state = parseStatus(statusText.orEmpty()),
            tags = allTags,
            chapters = chapters,
        )
    }

    override suspend fun getPages(chapter: MangaChapter): List<MangaPage> {
        val doc = webClient.httpGet(chapter.url.toAbsoluteUrl(domain)).parseHtml()
        return doc.select("#readerarea img").map { img ->
            val url = img.attr("data-src").ifEmpty { img.src().orEmpty() }
            MangaPage(
                id = generateUid(url),
                url = url,
                preview = null,
                source = source,
            )
        }
    }

    private fun parseStatus(status: String): MangaState? {
        return when {
            status.contains("ongoing", ignoreCase = true) -> MangaState.ONGOING
            status.contains("completed", ignoreCase = true) -> MangaState.FINISHED
            status.contains("hiatus", ignoreCase = true) -> MangaState.PAUSED
            status.contains("dropped", ignoreCase = true) -> MangaState.ABANDONED
            status.contains("canceled", ignoreCase = true) -> MangaState.ABANDONED
            else -> null
        }
    }

    private fun parseChapterDate(date: String?): Long {
        return try {
            SimpleDateFormat("MMMM dd, yyyy", sourceLocale).parse(date?.trim()).time
        } catch (_: Exception) {
            0L
        }
    }

    private suspend fun fetchAvailableTags(): Set<MangaTag> {
        val doc = webClient.httpGet("https://$domain/manga/").parseHtml()
        return doc.select("ul.genrez li").mapNotNullToSet { li ->
            val key = li.selectFirst("input")?.attrOrNull("value") ?: return@mapNotNullToSet null
            val title = li.selectFirst("label")?.textOrNull()?.toTitleCase() ?: return@mapNotNullToSet null
            MangaTag(
                key = key,
                title = title,
                source = source,
            )
        }
    }
}
