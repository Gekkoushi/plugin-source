package tsuki.site.madara.pt

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaParserSource
import tsuki.model.MangaTag
import tsuki.site.madara.MadaraParser
import tsuki.util.mapNotNullToSet
import tsuki.util.parseHtml
import tsuki.util.toTitleCase

@MangaSourceParser("MRBENNE", "MrBenne", "pt", ContentType.HENTAI)
internal class MrBenne(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MRBENNE, "mrbenne.com", 10) {
	override val datePattern: String = "dd/MM/yyyy"

	override suspend fun fetchAvailableTags(): Set<MangaTag> {
		val doc = webClient.httpGet("https://$domain/?s=&post_type=wp-manga").parseHtml()
		val body = doc.body()
		val root = body.selectFirst("div.form-group.checkbox-group")
		val list = root?.select("div.checkbox").orEmpty()
		val keySet = HashSet<String>(list.size)
		return list.mapNotNullToSet { div ->
			val input = div.selectFirst("input") ?: return@mapNotNullToSet null
			val label = div.selectFirst("label") ?: return@mapNotNullToSet null
			val tag = input.attr("value")
			if (tag.isEmpty() || !keySet.add(tag)) {
				return@mapNotNullToSet null
			}
			MangaTag(
				key = tag,
				title = label.ownText().ifEmpty {
					tag
				}.toTitleCase(sourceLocale),
				source = source,
			)
		}
	}
}
