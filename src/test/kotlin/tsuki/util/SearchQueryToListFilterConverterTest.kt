package tsuki.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import tsuki.model.ContentRating
import tsuki.model.ContentType.MANGA
import tsuki.model.ContentType.MANHUA
import tsuki.model.Demographic.SEINEN
import tsuki.model.MangaParserSource
import tsuki.model.MangaState
import tsuki.model.MangaTag
import tsuki.model.search.MangaSearchQuery
import tsuki.model.search.QueryCriteria.*
import tsuki.model.search.SearchableField.*
import java.util.*

class ConvertToMangaListFilterTest {

    @Test
    fun convertToMangaListFilterTest() {
        val tags = setOf(buildMangaTag("tag1"), buildMangaTag("tag2"))
        val excludedTags = setOf(buildMangaTag("exclude_tag"))
        val states = setOf(MangaState.ONGOING)
        val contentRatings = setOf(ContentRating.SAFE)
        val contentTypes = setOf(MANGA, MANHUA)
        val demographics = setOf(SEINEN)

        val query = MangaSearchQuery.Builder()
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

        val listFilter = convertToMangaListFilter(query)

        assertEquals(listFilter.query, "title_name")
        assertEquals(listFilter.tags, tags)
        assertEquals(listFilter.tagsExclude, excludedTags)
        assertEquals(listFilter.locale, Locale.ENGLISH)
        assertEquals(listFilter.originalLocale, Locale.JAPANESE)
        assertEquals(listFilter.states, states)
        assertEquals(listFilter.contentRating, contentRatings)
        assertEquals(listFilter.types, contentTypes)
        assertEquals(listFilter.demographics, demographics)
        assertEquals(listFilter.year, 2020)
        assertEquals(listFilter.yearFrom, 1997)
        assertEquals(listFilter.yearTo, 2024)
    }

    @Test
    fun convertToMangaListFilterWithMultipleTagsIncludeTest() {
        val tags1 = setOf(buildMangaTag("tag1"), buildMangaTag("tag2"))
        val tags2 = setOf(buildMangaTag("tag3"), buildMangaTag("tag4"))

        val query = MangaSearchQuery.Builder()
            .criterion(Include(TAG, tags1))
            .criterion(Include(TAG, tags2))
            .build()

        val listFilter = convertToMangaListFilter(query)

        assertEquals(listFilter.tags, tags1 union tags2)
    }

    @Test
    fun convertToMangaListFilterWithUnsupportedFieldTest() {
        val query = MangaSearchQuery.Builder()
            .criterion(Include(AUTHOR, setOf(buildMangaTag("author"))))
            .build()

        val exception = assertThrows<IllegalArgumentException> {
            convertToMangaListFilter(query)
        }

        assert(exception.message!!.contains("Unsupported field for Include criterion: AUTHOR"))
    }

    private fun buildMangaTag(name: String): MangaTag {
        return MangaTag(
            key = "${name}Key",
            title = name,
            source = MangaParserSource.MANGADEX,
        )
    }
}
