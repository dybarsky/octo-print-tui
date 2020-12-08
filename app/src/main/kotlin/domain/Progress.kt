package domain

import kotlinx.serialization.Serializable

@Serializable
data class Progress(
    val completion: Double?,
    val printTime: Int?,
    val printTimeLeft: Int?,
)