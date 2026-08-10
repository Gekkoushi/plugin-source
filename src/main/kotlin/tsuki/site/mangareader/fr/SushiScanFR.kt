package tsuki.site.mangareader.fr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaChapter
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaPage
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("SUSHISCANFR", "SushiScan.fr", "fr")
internal class SushiScanFR(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.SUSHISCANFR, "sushiscan.fr", pageSize = 36, searchPageSize = 10) {
	override val listUrl = "/catalogue"
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)

	private val pagesCache = object : LinkedHashMap<String, List<MangaPage>>(64, 0.75f, true) {
		override fun removeEldestEntry(eldest: MutableMap.MutableEntry<String, List<MangaPage>>?): Boolean {
			return size > PAGES_CACHE_SIZE
		}
	}

	override suspend fun getPages(chapter: MangaChapter): List<MangaPage> {
		synchronized(pagesCache) {
			pagesCache[chapter.url]?.let { return it }
		}
		val pages = super.getPages(chapter)
		if (pages.isNotEmpty()) {
			synchronized(pagesCache) {
				pagesCache[chapter.url] = pages
			}
		}
		return pages
	}

	private companion object {
		private const val PAGES_CACHE_SIZE = 200
	}
}
