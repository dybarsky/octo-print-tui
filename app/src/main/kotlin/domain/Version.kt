package domain

import kotlinx.serialization.Serializable

@Serializable
data class Version(
        val api: String,
        val server: String,
        val text: String,
)