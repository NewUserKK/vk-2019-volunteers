package ru.wa285.volunteers.data.repository.person.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonUnwrapped
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import ru.wa285.volunteers.domain.person.model.Person
import java.util.*

data class PersonWithPassword @JsonCreator constructor(
    @JsonProperty("name") val name: String,
    @JsonProperty("surname") val surname: String,
    @JsonProperty("patronymic") val patronymic: String,
    @JsonProperty("phone") val phone: String?,
    @JsonProperty("email") val email: String?,
    @JsonProperty("login") val login: String,
    @JsonProperty("birthday") val birthday: Date,
    @JsonProperty("photoLink") val avatarUri: String?,
    @JsonProperty("id") val id: Long = 0,
    @JsonProperty("password") val password: String,
    @JsonProperty("vkToken") val vkToken: String? = null
)