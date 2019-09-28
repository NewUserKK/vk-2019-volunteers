package ru.wa285.volunteers.domain.achievement.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class Achievement @JsonCreator constructor(
    @JsonProperty("name") val name: String,
    @JsonProperty("logoUri") val logoUri: String,
    @JsonProperty("description") val description: String,
    @JsonProperty("id") val id: Long
) : Serializable