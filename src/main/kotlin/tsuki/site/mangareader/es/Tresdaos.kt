package tsuki.site.mangareader.es

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaListFilterCapabilities
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("TRESDAOS", "Tresdaos", "es")
internal class Tresdaos(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.TRESDAOS, "threedaos.zdrz.xyz", 20, 10) {
	override val filterCapabilities: MangaListFilterCapabilities
		get() = super.filterCapabilities.copy(
			isTagsExclusionSupported = false,
		)
}
