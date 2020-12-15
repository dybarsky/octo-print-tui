package domain

import kotlinx.serialization.Serializable

@Serializable
data class Job(
    val file: File,
    val filament: Filament?,
    val estimatedPrintTime: Double?,
)
