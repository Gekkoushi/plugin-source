package tsuki.site.liliana.vi

import org.jsoup.Jsoup
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaChapter
import tsuki.model.MangaPage
import tsuki.model.MangaParserSource
import tsuki.exception.ParseException
import tsuki.network.CommonHeaders
import tsuki.site.liliana.LilianaParser
import tsuki.util.json.getBooleanOrDefault
import tsuki.util.*

@MangaSourceParser("DOCTRUYEN5S", "DocTruyen5s", "vi")
internal class DocTruyen5s(context: MangaLoaderContext) :
	LilianaParser(context, MangaParserSource.DOCTRUYEN5S, "manga.io.vn", 42) {

	override fun getRequestHeaders() = super.getRequestHeaders().newBuilder()
		.add(CommonHeaders.REFERER, "no-referrer")
		.build()

	override suspend fun getPages(chapter: MangaChapter): List<MangaPage> {
		val fullUrl = chapter.url.toAbsoluteUrl(domain)
		val doc = webClient.httpGet(fullUrl).parseHtml()
		val script = doc.selectFirstOrThrow("script:containsData(const CHAPTER_ID)").data()
		val chapterId = script.substringAfter("const CHAPTER_ID = ", "").substringBefore(';', "")
		check(chapterId.isNotEmpty()) { ParseException("Không thể tìm thấy CHAPTER_ID, hãy kiểm tra nguồn!", fullUrl) }

		val ajaxUrl = buildString {
			append("https://")
			append(domain)
			append("/ajax/image/list/chap/")
			append(chapterId)
		}

		val responseJson = webClient.httpGet(ajaxUrl).parseJson()
		check(responseJson.getBooleanOrDefault("status", false)) { responseJson.getString("msg") }

		val pageListDoc = Jsoup.parse(responseJson.getString("html"))

		return pageListDoc.selectOrThrow("div.separator a").mapNotNull { element ->
			val originalUrl = element.attr("href").takeIf { it.isNotEmpty() } ?: element.attr("src")
			if (originalUrl.isEmpty()) return@mapNotNull null

			val workingUrl = addCdnServers(originalUrl).firstOrNull { url ->
				checkMangaImgs(url)
			}

			workingUrl?.let { url ->
				MangaPage(
					id = generateUid(url),
					url = url,
					preview = null,
					source = source,
				)
			}
		}
	}

	private fun addCdnServers(url: String): List<String> {
		if (!url.startsWith("http")) return emptyList()

		val urlFinal = url.replace("https://", "")
		return listOf(
			url,
			"https://proxy.luce.workers.dev/$url",
			"https://images2-focus-opensocial.googleusercontent.com/gadgets/proxy?url=$url&container=focus&gadget=a&no_expand=1&resize_h=0&rewriteMime=image/*",
			"https://i0.wp.com/$urlFinal",
			"https://cdn.statically.io/img/$urlFinal"
		)
	}

	private suspend fun checkMangaImgs(url: String): Boolean {
		return try {
			val response = webClient.httpHead(url)
			val contentType = response.header(CommonHeaders.CONTENT_TYPE) ?: ""
			contentType.startsWith("image/")
		} catch (_: Exception) {
			false
		}
	}
}
