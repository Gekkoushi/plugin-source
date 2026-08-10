package tsuki.site.zeistmanga.id

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.zeistmanga.ZeistMangaParser
import tsuki.model.MangaChapter
import tsuki.model.MangaPage
import tsuki.util.toAbsoluteUrl
import tsuki.util.generateUid
import tsuki.util.parseHtml
import tsuki.util.selectFirstOrThrow

@MangaSourceParser("ULASCOMIC", "UlasComic", "id")
internal class UlasComic(context: MangaLoaderContext):
	ZeistMangaParser(context, MangaParserSource.ULASCOMIC, "www.ulascomic00.xyz") {
	
	override suspend fun getPages(chapter: MangaChapter): List<MangaPage> {
		val doc = webClient.httpGet(chapter.url.toAbsoluteUrl(domain)).parseHtml()
		return doc.selectFirstOrThrow("script:containsData(config['chapterImage'])")
			.data()
			.substringAfter("config['chapterImage'] = [")
			.substringBefore("];")
			.split("\",")
			.map { url ->
				val cleanUrl = url.trim().replace("\"", "")
				MangaPage(
					id = generateUid(cleanUrl),
					url = cleanUrl,
					preview = null,
					source = source,
				)
			}
	}
}
