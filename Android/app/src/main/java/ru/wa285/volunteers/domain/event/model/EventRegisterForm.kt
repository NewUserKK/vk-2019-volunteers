package ru.wa285.volunteers.domain.event.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import ru.wa285.volunteers.domain.person.model.Person
import ru.wa285.volunteers.domain.role.model.Role
import java.util.*

data class EventRegisterForm @JsonCreator constructor(
    @JsonProperty("comment") val comment: String,
    @JsonProperty("endDate") val endDate: Date,
    @JsonProperty("event") val event: Event,
    @JsonProperty("id") val id: Long = 0,
    @JsonProperty("role") val role: Role?,
    @JsonProperty("startDate") val startDate: Date,
    @JsonProperty("status") val status: Long = 0,
    @JsonProperty("user") val user: Person
)