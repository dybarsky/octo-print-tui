package domain

import kotlinx.serialization.Serializable

@Serializable
data class Tool(
    val length: Double?,
    val volume: Double?,
)
