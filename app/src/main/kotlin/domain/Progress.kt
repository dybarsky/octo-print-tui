package domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Progress(

    @SerialName("completion")
    val completion: Double?,

    @SerialName("printTime")
    val printTime: Long?,

    @SerialName("printTimeLeft")
    val printTimeLeft: Long?,
)