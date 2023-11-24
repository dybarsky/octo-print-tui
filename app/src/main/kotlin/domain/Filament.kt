package domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Filament(

    @SerialName("length")
    val length: Double?,

    @SerialName("volume")
    val volume: Double?,
)
