package ru.wa285.volunteers.domain.person.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import java.util.*
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id



data class Person @JsonCreator constructor(
    @JsonProperty("name") val name: String,
    @JsonProperty("surname") val surname: String,
    @JsonProperty("patronymic") val patronymic: String,
    @JsonProperty("phone") val phone: String?,
    @JsonProperty("email") val email: String?,
    @JsonProperty("login") val login: String,
    @JsonProperty("birthday") val birthday: Date,
    @JsonProperty("photoLink") val avatarUri: String?,
    @JsonProperty("id") val id: Long = 0
): Serializable