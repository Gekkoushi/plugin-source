package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaChapter
import tsuki.model.MangaPage
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser
import tsuki.util.toAbsoluteUrl
import tsuki.util.toRelativeUrl

@MangaSourceParser("HIPERDEX", "HiperToon", "en", ContentType.HENTAI)
internal class HiperDex(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.HIPERDEX, "hiperdex.com", 36) {

	override val listUrl = ""

	override suspend fun getPages(chapter: MangaChapter): List<MangaPage> {
		// Remove ?style=list parameter from chapter URLs
		val absoluteUrl = chapter.url.toAbsoluteUrl(domain)
		val cleanUrl = if (absoluteUrl.contains("?style=list")) {
			absoluteUrl.replace("?style=list", "")
				.replace("&style=list", "")
		} else {
			absoluteUrl
		}
		val relativeCleanUrl = cleanUrl.toRelativeUrl(domain)
		val modifiedChapter = chapter.copy(url = relativeCleanUrl)
		return super.getPages(modifiedChapter)
	}
}
