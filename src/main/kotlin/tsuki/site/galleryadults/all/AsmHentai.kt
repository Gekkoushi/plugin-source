package tsuki.site.galleryadults.all

import org.jsoup.nodes.Element
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.model.MangaTag
import tsuki.site.galleryadults.GalleryAdultsParser
import tsuki.util.mapToSet
import tsuki.util.removeSuffix
import java.util.*

@MangaSourceParser("ASMHENTAI", "AsmHentai", type = ContentType.HENTAI)
internal class AsmHentai(context: MangaLoaderContext) :
	GalleryAdultsParser(context, MangaParserSource.ASMHENTAI, "asmhentai.com") {

	override val selectGallery = ".preview_item"
	override val selectGalleryLink = ".image a"
	override val selectGalleryImg = ".image img"
	override val pathTagUrl = "/tags/?page="
	override val selectTags = ".tags_page ul.tags"
	override val selectAuthor = "div.tags:contains(Artists:) .tag_list a span.tag"
	override val idImg = "fimg"

	override suspend fun getFilterOptions() = super.getFilterOptions().copy(
		availableLocales = setOf(
			Locale.ENGLISH,
			Locale.JAPANESE,
			Locale.CHINESE,
			Locale("tr"),
		),
	)

	override fun Element.parseTags() = select("a").mapToSet {
		val key = it.attr("href").removeSuffix('/').substringAfterLast('/')
		val name = it.selectFirst(".tag")?.html()?.substringBefore("<") ?: it.html().substringBefore("<")
		MangaTag(
			key = key,
			title = name,
			source = source,
		)
	}
}
