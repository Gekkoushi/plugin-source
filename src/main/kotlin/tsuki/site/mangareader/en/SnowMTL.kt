package tsuki.site.mangareader.en

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.mangareader.MangaReaderParser

@MangaSourceParser("SNOWMACHINETRANSLATION", "Snow Machine Translation", "en")

internal class SnowMachineTranslation(context: MangaLoaderContext) :
    MangaReaderParser(
        context,
        MangaParserSource.SNOWMACHINETRANSLATION,
        "snowmachinetranslation.com",
        pageSize = 24,
        searchPageSize = 10
    ) {
        override val listUrl = "/manga"
}
