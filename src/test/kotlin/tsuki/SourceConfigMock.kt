package tsuki

import tsuki.config.ConfigKey
import tsuki.config.MangaSourceConfig

internal class SourceConfigMock : MangaSourceConfig {

	override fun <T> get(key: ConfigKey<T>): T = key.defaultValue
}