package ru.wa285.volunteers.data.repository.person.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonUnwrapped
import ru.wa285.volunteers.domain.person.model.Person

data class PersonWithPassword @JsonCreator constructor(
    @JsonUnwrapped val person: Person,
    @JsonProperty("password") val password: String,
    @JsonProperty("vkToken") val vkToken: String? = null
)