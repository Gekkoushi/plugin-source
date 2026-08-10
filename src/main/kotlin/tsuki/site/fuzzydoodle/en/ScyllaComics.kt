package tsuki.site.fuzzydoodle.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.ContentType
import tsuki.model.MangaListFilterOptions
import tsuki.model.MangaParserSource
import tsuki.site.fuzzydoodle.FuzzyDoodleParser
import java.util.EnumSet

@MangaSourceParser("SCYLLACOMICS", "ScyllaComics", "en")
internal class ScyllaComics(context: MangaLoaderContext) :
	FuzzyDoodleParser(context, MangaParserSource.SCYLLACOMICS, "scyllacomics.xyz") {

	override suspend fun getFilterOptions() = MangaListFilterOptions(
		availableContentTypes = EnumSet.of(
			ContentType.MANGA,
			ContentType.MANHWA,
			ContentType.MANHUA,
		),
	)
}
