package domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class File(

    @SerialName("name")
    val name: String?,

    @SerialName("size")
    val size: Long?,

    @SerialName("date")
    val date: Long?,
)