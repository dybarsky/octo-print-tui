package domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Job(

    @SerialName("file")
    val file: File,

    @SerialName("filament")
    val filament: Filament?,

    @SerialName("estimatedPrintTime")
    val estimatedPrintTime: Double?,
)
