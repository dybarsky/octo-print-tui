package util

import java.io.File
import java.util.*

data class Config private constructor(
        val url: String,
        val key: String,
        val gap: Int,
) {

    companion object {
        private const val PROP_URL = "url"
        private const val PROP_KEY = "key"
        private const val PROP_GAP = "gap"

        private const val DEFAULT_TIME = 1 // seconds
        private const val FALLBACK_URL = "http://octopi.local"
        private const val CONFIG_PATH = "/.config/octo-print-tui"

        fun instance(): Config {
            val file = File(System.getProperty("user.home") + CONFIG_PATH)
            val properties = Properties()
            try {
                properties.load(file.inputStream())
            } catch (e: Exception) {
                Log.append("Can't load config properties file: $e")
            }
            val gap = properties.getProperty(PROP_GAP)?.toIntOrNull() ?: DEFAULT_TIME
            val url = properties.getProperty(PROP_URL) ?: FALLBACK_URL
            val key = properties.getProperty(PROP_KEY) ?: ""
            return Config(url, key, gap)
        }
    }
}