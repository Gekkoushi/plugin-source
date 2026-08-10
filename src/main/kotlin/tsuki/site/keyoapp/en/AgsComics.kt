package tsuki.site.keyoapp.en

import org.jsoup.nodes.Element
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.keyoapp.KeyoappParser
import tsuki.util.styleValueOrNull
import tsuki.util.cssUrl
import tsuki.Broken

@Broken("Site is online but parser is broken — layout/API changed, needs rewrite") // It seems like the site is dead.
@MangaSourceParser("AGSCOMICS", "AgsComics", "en")
internal class AgsComics(context: MangaLoaderContext) :
	KeyoappParser(context, MangaParserSource.AGSCOMICS, "agrcomics.com") {

	override val cover: (Element) -> String? = { div ->
		val coverDiv = div.selectFirst("div.bg-cover[style*=background-image]")
			?: div.takeIf { it.hasClass("bg-cover") && it.hasAttr("style") }
			?: div.selectFirst("[style*=background-image]")
			?: throw Exception("Element or image url not found")

		coverDiv.styleValueOrNull("background-image")?.cssUrl()
	}

}
