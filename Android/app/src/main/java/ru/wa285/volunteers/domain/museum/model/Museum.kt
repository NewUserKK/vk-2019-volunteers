package ru.wa285.volunteers.domain.museum.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class Museum @JsonCreator constructor(
    @JsonProperty("name") val name: String,
//    @JsonProperty("description") val description: String,
    // TODO: address
    @JsonProperty("city") val address: String,
    @JsonProperty("photo") val logoUri: String?,
    @JsonProperty("id") val id: Long
): Serializable