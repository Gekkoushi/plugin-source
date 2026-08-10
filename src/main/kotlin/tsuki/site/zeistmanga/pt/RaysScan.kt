package tsuki.site.zeistmanga.pt

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.model.MangaTag
import tsuki.site.zeistmanga.ZeistMangaParser

@MangaSourceParser("RAYSSCAN", "RaysScan", "pt")
internal class RaysScan(context: MangaLoaderContext) :
	ZeistMangaParser(context, MangaParserSource.RAYSSCAN, "raysscan.blogspot.com") {

	override suspend fun getFilterOptions() = super.getFilterOptions().copy(
		availableStates = emptySet(),
	)

	override suspend fun fetchAvailableTags(): Set<MangaTag> = emptySet()
}


