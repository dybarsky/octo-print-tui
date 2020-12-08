package domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Current(
    @SerialName("state")
    private val raw: String,
    val job: Job? = null,
    val progress: Progress? = null,
) {

    /** Cuts off error message from raw status string and converts to state enum */
    @Transient
    val state: State = raw.indexOf(' ').let { index ->
        val value = if (index == -1) raw else raw.substring(0, index)
        State.valueOf(value)
    }
}