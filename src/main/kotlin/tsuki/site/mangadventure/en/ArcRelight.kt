package tsuki.site.mangadventure.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.Manga
import tsuki.model.MangaParserSource
import tsuki.site.mangadventure.MangAdventureParser

@MangaSourceParser("ARCRELIGHT", "Arc-Relight", "en")
internal class ArcRelight(context: MangaLoaderContext) :
	MangAdventureParser(context, MangaParserSource.ARCRELIGHT, "arc-relight.com", pageSize = 10) {
	private val franchises = setOf("Jubilee", "Steins;Gate", "Robotics;Notes", "Chaos;Head")

	override suspend fun getRelatedManga(seed: Manga): List<Manga> {
		val tags = franchises.filter { franchise ->
			seed.tags.find { it.key == franchise } != null
		}
		if (tags.isEmpty()) return emptyList()
		val url = apiUrl.addEncodedPathSegment("series")
			.addQueryParameter("categories", tags.joinToString(","))
		return getManga(url.get())
	}
}
