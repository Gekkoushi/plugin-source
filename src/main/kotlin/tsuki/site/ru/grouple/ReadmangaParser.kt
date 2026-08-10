package tsuki.site.ru.grouple

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.config.ConfigKey
import tsuki.model.MangaParserSource
import tsuki.network.CommonHeaders

@MangaSourceParser("READMANGA_RU", "ReadManga", "ru")
internal class ReadmangaParser(
    context: MangaLoaderContext,
) : GroupleParser(context, MangaParserSource.READMANGA_RU, 1) {

    override val configKeyDomain = ConfigKey.Domain(*domains)

    override fun getRequestHeaders() = super.getRequestHeaders().newBuilder()
        .add(CommonHeaders.REFERER, "https://$domain/")
        .build()

    companion object {

        val domains = arrayOf(
            "a.zazaza.me",
            "3.readmanga.ru",
        )
    }
}
