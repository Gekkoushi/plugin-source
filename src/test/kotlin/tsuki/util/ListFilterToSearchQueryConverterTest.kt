package tsuki.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import tsuki.model.*
import tsuki.model.ContentType.MANGA
import tsuki.model.ContentType.MANHUA
import tsuki.model.Demographic.SEINEN
import tsuki.model.search.MangaSearchQuery
import tsuki.model.search.QueryCriteria.*
import tsuki.model.search.SearchableField.*
import java.util.*

class ListFilterToSearchQueryConverterTest {

    @Test
    fun convertToMangaSearchQueryTest() {
        val tags = setOf(buildMangaTag("tag1"), buildMangaTag("tag2"))
        val excludedTags = setOf(buildMangaTag("exclude_tag"))
        val states = setOf(MangaState.ONGOING)
        val contentRatings = setOf(ContentRating.SAFE)
        val contentTypes = setOf(MANGA, MANHUA)
        val demographics = setOf(SEINEN)

        val filter = MangaListFilter(
            query = "title_name",
            tags = tags,
            tagsExclude = excludedTags,
            locale = Locale.ENGLISH,
            originalLocale = Locale.JAPANESE,
            states = states,
            contentRating = contentRatings,
            types = contentTypes,
            demographics = demographics,
            year = 2020,
            yearFrom = 1997,
            yearTo = 2024,
        )

        val searchQuery = convertToMangaSearchQuery(0, SortOrder.NEWEST, filter)

        val expectedQuery = MangaSearchQuery.Builder()
            .offset(0)
            .order(SortOrder.NEWEST)
            .criterion(Match(TITLE_NAME, "title_name"))
            .criterion(Include(TAG, tags))
            .criterion(Exclude(TAG, excludedTags))
            .criterion(Include(LANGUAGE, setOf(Locale.ENGLISH)))
            .criterion(Include(ORIGINAL_LANGUAGE, setOf(Locale.JAPANESE)))
            .criterion(Include(STATE, states))
            .criterion(Include(CONTENT_RATING, contentRatings))
            .criterion(Include(CONTENT_TYPE, contentTypes))
            .criterion(Include(DEMOGRAPHIC, demographics))
            .criterion(Range(PUBLICATION_YEAR, 1997, 2024))
            .criterion(Match(PUBLICATION_YEAR, 2020))
            .build()

        assertEquals(expectedQuery, searchQuery)
    }

    @Test
    fun convertToMangaSearchQueryWithEmptyFieldsTest() {
        val filter = MangaListFilter()

        val searchQuery = convertToMangaSearchQuery(0, SortOrder.NEWEST, filter)

        assertEquals(MangaSearchQuery.Builder().offset(0).order(SortOrder.NEWEST).build(), searchQuery)
    }

    private fun buildMangaTag(name: String): MangaTag {
        return MangaTag(
            key = "${name}Key",
            title = name,
            source = MangaParserSource.MANGADEX,
        )
    }
}
