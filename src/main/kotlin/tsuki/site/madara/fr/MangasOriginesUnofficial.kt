package tsuki.site.madara.fr

import tsuki.Broken
import tsuki.MangaLoaderContext
import tsuki.MangaSourceParser
import tsuki.model.MangaParserSource
import tsuki.site.madara.MadaraParser

@Broken ("Not dead, changed template")
@MangaSourceParser("MANGASORIGINESUNOFFICIAL", "CrunchyScan", "fr")
internal class MangasOriginesUnofficial(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGASORIGINESUNOFFICIAL, "crunchyscan.fr")
