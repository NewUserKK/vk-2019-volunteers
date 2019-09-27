package ru.newuserkk.volunteers.domain.museum.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class Museum @JsonCreator constructor(
    @JsonProperty("name") val name: String,
    @JsonProperty("city") val address: String,
    @JsonProperty("photo") val logoUri: String,
    @JsonProperty("id") val id: Long
)