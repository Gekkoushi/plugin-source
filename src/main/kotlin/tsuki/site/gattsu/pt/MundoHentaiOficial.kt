package tsuki.site.gattsu.pt

import org.jsoup.nodes.Document
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.*
import tsuki.site.gattsu.GattsuParser
import tsuki.util.attrAsAbsoluteUrl
import tsuki.util.generateUid
import tsuki.util.selectLastOrThrow
import tsuki.util.src

@MangaSourceParser("MUNDOHENTAIOFICIAL", "MundoHentaiOficial", "pt", ContentType.HENTAI)
internal class MundoHentaiOficial(context: MangaLoaderContext) :
	GattsuParser(context, MangaParserSource.MUNDOHENTAIOFICIAL, "mundohentaioficial.com") {

	override val tagUrl = "tags"

	override fun parseMangaList(doc: Document): List<Manga> {
		return doc.select("div.lista ul li, div.videos div.video").mapNotNull { li ->
			val a = li.selectLastOrThrow("a")
			val href = a.attrAsAbsoluteUrl("href")
			if (!href.contains(domain)) {
				//Some sources include ads in manga lists
				return@mapNotNull null
			}
			Manga(
				id = generateUid(href),
				url = href,
				publicUrl = href,
				title = li.selectLastOrThrow(".thumb-titulo, .video-titulo").text(),
				coverUrl = li.selectFirst("img")?.src(),
				altTitles = emptySet(),
				rating = RATING_UNKNOWN,
				tags = emptySet(),
				description = null,
				state = null,
				authors = emptySet(),
				contentRating = if (isNsfwSource) ContentRating.ADULT else null,
				source = source,
			)
		}
	}
}
