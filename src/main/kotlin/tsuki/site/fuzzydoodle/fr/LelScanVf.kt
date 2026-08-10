package tsuki.site.fuzzydoodle.fr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.model.MangaState
import tsuki.site.fuzzydoodle.FuzzyDoodleParser
import java.util.*

@MangaSourceParser("LELSCANVF", "LelScanFr", "fr")
internal class LelScanVf(context: MangaLoaderContext) :
	FuzzyDoodleParser(context, MangaParserSource.LELSCANVF, "lelscanfr.com") {

	override val ongoingValue = "en-cours"
	override val finishedValue = "termin"

	override suspend fun getFilterOptions() = super.getFilterOptions().copy(
		availableStates = EnumSet.of(MangaState.ONGOING, MangaState.FINISHED),
	)
}
