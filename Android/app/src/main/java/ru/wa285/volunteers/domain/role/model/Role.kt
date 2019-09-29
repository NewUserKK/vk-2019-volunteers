package ru.wa285.volunteers.domain.role.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class Role @JsonCreator constructor(
    @JsonProperty("description") val description: String,
    @JsonProperty("id") val id: Long = 0,
    @JsonProperty("name") val name: String
)
