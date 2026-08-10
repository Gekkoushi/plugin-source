package tsuki.site.ru.grouple

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.config.ConfigKey
import tsuki.model.MangaParserSource
import tsuki.network.CommonHeaders

@MangaSourceParser("SEIMANGA", "SeiManga", "ru")
internal class SeiMangaParser(
	context: MangaLoaderContext,
) : GroupleParser(context, MangaParserSource.SEIMANGA, 21) {

	override val configKeyDomain = ConfigKey.Domain(*domains)

	override fun getRequestHeaders() = super.getRequestHeaders().newBuilder()
		.add(CommonHeaders.REFERER, "https://$domain/")
		.build()

	companion object {

		val domains = arrayOf(
			"1.seimanga.me",
		)
	}
}
