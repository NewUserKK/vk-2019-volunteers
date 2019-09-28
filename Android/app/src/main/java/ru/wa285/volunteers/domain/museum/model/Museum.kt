package ru.wa285.volunteers.domain.museum.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class Museum @JsonCreator constructor(
    @JsonProperty("name") val name: String,
    // TODO: address
    @JsonProperty("city") val address: String,
    @JsonProperty("photo") val logoUri: String,
    @JsonProperty("id") val id: Long
)