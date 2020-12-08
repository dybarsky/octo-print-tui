package domain

import kotlinx.serialization.Serializable

@Serializable
data class File(
    val name: String?,
    val size: String?,
    val date: String?,
)