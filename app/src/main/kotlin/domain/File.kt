package domain

import kotlinx.serialization.Serializable

@Serializable
data class File(
    val name: String?,
    val size: Long?,
    val date: Long?,
)