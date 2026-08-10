package tsuki.site.heancms.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.*
import tsuki.site.heancms.HeanCms
import tsuki.util.*

@MangaSourceParser("LUASCANS", "luaComic.net", "en")
internal class LuaScans(context: MangaLoaderContext) :
	HeanCms(context, MangaParserSource.LUASCANS, "luacomic.org") {

	// The reader is a Next.js client-rendered page: page image URLs are embedded (with escaped
	// slashes) in the server-sent flight data under /uploads/series/..., already in page order.
	private val pageUrlRegex =
		Regex("""https://[^"'\\ ]*?/uploads/series/[^"'\\ ]+?\.(?:webp|jpg|jpeg|png|avif)(?:\.(?:webp|jpg|jpeg|png|avif))?""")

	override suspend fun getPages(chapter: MangaChapter): List<MangaPage> {
		val fullUrl = chapter.url.toAbsoluteUrl(domain)
		val html = webClient.httpGet(fullUrl).parseRaw().replace("\\/", "/")
		return pageUrlRegex.findAll(html)
			.map { it.value }
			.distinct()
			.map { url ->
				MangaPage(
					id = generateUid(url),
					url = url,
					preview = null,
					source = source,
				)
			}
			.toList()
	}
}
