package tsuki.site.natsu.id

import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.config.ConfigKey
import tsuki.model.MangaParserSource
import tsuki.site.natsu.NatsuParser

@MangaSourceParser("IKIRU", "Ikiru", "id")
internal class Ikiru(context: MangaLoaderContext) :
    NatsuParser(context, MangaParserSource.IKIRU, 24) {

    override val configKeyDomain = ConfigKey.Domain("02.ikiru.wtf")

	override fun onCreateConfig(keys: MutableCollection<ConfigKey<*>>) {
		super.onCreateConfig(keys)
		keys.add(userAgentKey)
	}
}
