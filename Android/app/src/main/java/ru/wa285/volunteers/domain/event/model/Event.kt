package ru.wa285.volunteers.domain.event.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import ru.wa285.volunteers.domain.museum.model.Museum
import java.io.Serializable
import java.util.*


data class Event @JsonCreator constructor(
    @JsonProperty("title") val title: String,
    @JsonProperty("description") val description: String,
    @JsonProperty("museum") val museum: Museum,
    @JsonProperty("photoLink") val photoLink: String? = null,
    @JsonProperty("startDate") val startDate: Date,
    @JsonProperty("endDate") val endDate: Date,
    @JsonProperty("type") val type: String,
    @JsonProperty("requiredRating") val requiredRating: Int,
    @JsonProperty("id") val id: Long = 0
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Event

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}