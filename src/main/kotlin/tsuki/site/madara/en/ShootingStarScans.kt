package tsuki.site.madara.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@MangaSourceParser("SHOOTINGSTARSCANS", "Shooting Star Scans", "en")
internal class ShootingStarScans(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.SHOOTINGSTARSCANS, "shootingstarscans.com") {
	override val tagPrefix = "manga-tag/"
}
