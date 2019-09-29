package ru.wa285.volunteers.domain.museum.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class Museum @JsonCreator constructor(
    @JsonProperty("name") val name: String,
    @JsonProperty("description") val description: String,
    // TODO: address
    @JsonProperty("address") val address: String,
    @JsonProperty("city") val city: String,
    @JsonProperty("hours") val hours: String,
    @JsonProperty("photo") val photo: String?,
    @JsonProperty("id") val id: Long
): Serializable