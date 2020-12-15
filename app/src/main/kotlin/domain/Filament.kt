package domain

import kotlinx.serialization.Serializable

@Serializable
data class Filament(
    val tool0: Tool
) {

    @Serializable
    data class Tool(
        val length: Double,
        val volume: Double,
    )
}
