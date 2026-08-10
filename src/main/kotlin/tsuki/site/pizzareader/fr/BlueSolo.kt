package tsuki.site.pizzareader.fr

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.model.SortOrder
import tsuki.site.pizzareader.PizzaReaderParser
import java.util.EnumSet

@MangaSourceParser("BLUESOLO", "BlueSolo", "fr")
internal class BlueSolo(context: MangaLoaderContext) :
	PizzaReaderParser(context, MangaParserSource.BLUESOLO, "bluesolo.org") {

	override val availableSortOrders: Set<SortOrder> = EnumSet.of(
		SortOrder.ALPHABETICAL,
		SortOrder.UPDATED,
		SortOrder.UPDATED_ASC,
	)

	override val ongoingFilter = "en cours"
	override val completedFilter = "terminé"
	override val hiatusFilter = "hiatus"
	override val abandonedFilter = "cancel"
}
