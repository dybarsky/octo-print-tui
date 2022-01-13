package domain

import kotlinx.serialization.Serializable

@Serializable
data class Filament(
    val length: Double?,
    val volume: Double?,
)
