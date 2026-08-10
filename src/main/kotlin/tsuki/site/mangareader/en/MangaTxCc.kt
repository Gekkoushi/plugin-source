package tsuki.site.mangareader.en

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.Manga
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser
import tsuki.util.LinkResolver
import tsuki.util.generateUid
import tsuki.util.nullIfEmpty

@MangaSourceParser("MANGATX_CC", "MangaTx.cc", "en")
internal class MangaTxCc(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.MANGATX_CC, "mangatx.cc", 30, 21) {
	override val datePattern = "dd-MM-yyyy"
	override val listUrl = "/manga-list"

	override fun intercept(chain: Interceptor.Chain): Response {
		val request = chain.request()
		val url = request.url

		// Normalize old URL formats to /manga/
		// Site used to have /manhwa/, /manhua/, /comic/ but now only /manga/ works properly
		val pathSegments = url.pathSegments
		if (pathSegments.isNotEmpty()) {
			val firstSegment = pathSegments[0]
			if (firstSegment in listOf("manhwa", "manhua", "comic") && pathSegments.size >= 2) {
				val newUrl = url.newBuilder()
					.setPathSegment(0, "manga")
					.build()
				val newRequest = request.newBuilder()
					.url(newUrl)
					.build()
				return chain.proceed(newRequest)
			}
		}

		return super.intercept(chain)
	}

	override suspend fun resolveLink(resolver: LinkResolver, link: HttpUrl): Manga? {
		// Handle old URL formats: /manhwa/slug/, /manhua/slug/, /comic/slug/
		val pathSegment = link.pathSegments.getOrNull(0) ?: return null
		val mangaSlug = when (pathSegment) {
			"manga", "manhwa", "manhua", "comic" -> link.pathSegments.getOrNull(1)?.nullIfEmpty()
			else -> return null
		} ?: return null
		val url = "/manga/$mangaSlug/"
		return resolver.resolveManga(this, url, generateUid(url))
	}
}
