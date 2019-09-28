package ru.wa285.volunteers.domain.person.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class PersonAuthCredentials @JsonCreator constructor(
    @JsonProperty("login") val login: String,
    @JsonProperty("password") val password: String
)