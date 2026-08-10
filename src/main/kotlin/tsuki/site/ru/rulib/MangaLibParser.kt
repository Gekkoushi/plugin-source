package tsuki.site.ru.rulib

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.exception.AuthRequiredException
import tsuki.exception.NotFoundException
import tsuki.model.MangaChapter
import tsuki.model.MangaPage
import tsuki.model.MangaParserSource

@MangaSourceParser("MANGALIB", "MangaLib", "ru")
internal class MangaLibParser(
	context: MangaLoaderContext,
) : LibSocialParser(
	context = context,
	source = MangaParserSource.MANGALIB,
	siteId = 1,
	siteDomains = arrayOf("mangalib.org", "mangalib.me"),
) {

	override suspend fun getPages(chapter: MangaChapter): List<MangaPage> = try {
		super.getPages(chapter)
	} catch (e: NotFoundException) {
		throw AuthRequiredException(source, e)
	}
}
