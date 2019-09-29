package ru.wa285.volunteers.domain.event.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import ru.wa285.volunteers.domain.museum.model.Museum
import java.io.Serializable
import java.util.*


data class Event @JsonCreator constructor(
    @JsonProperty("title") val name: String,
    @JsonProperty("description") val description: String,
    @JsonProperty("museum") val museum: Museum,
    @JsonProperty("photoLink") val avatarUri: String? = null,
    @JsonProperty("startDate") val dateStart: Date,
    @JsonProperty("endDate") val dateEnd: Date,
    @JsonProperty("type") val type: String,
    @JsonProperty("requiredRating") val rating: Int,
    @JsonProperty("id") val id: Long = 0
) : Serializable