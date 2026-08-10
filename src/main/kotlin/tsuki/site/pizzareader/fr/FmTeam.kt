package tsuki.site.pizzareader.fr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.model.SortOrder
import tsuki.site.pizzareader.PizzaReaderParser
import java.util.EnumSet

@MangaSourceParser("FMTEAM", "FmTeam", "fr")
internal class FmTeam(context: MangaLoaderContext) :
	PizzaReaderParser(context, MangaParserSource.FMTEAM, "fmteam.fr") {
	override val availableSortOrders: Set<SortOrder> = EnumSet.of(
		SortOrder.ALPHABETICAL,
		SortOrder.UPDATED,
		SortOrder.UPDATED_ASC,
	)
	override val ongoingFilter = "en cours"
	override val completedFilter = "terminé"
}
