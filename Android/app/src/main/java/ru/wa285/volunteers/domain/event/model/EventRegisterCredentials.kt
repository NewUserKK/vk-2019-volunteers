package ru.wa285.volunteers.domain.event.model

import android.app.Person
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class EventRegisterCredentials @JsonCreator constructor(
    @JsonProperty("comment") val comment: String,
    @JsonProperty("endDate") val endDate: Date,
    @JsonProperty("event") val event: Event,
    @JsonProperty("id") val id: Long = 0,
    @JsonProperty("role") val role: String,
    @JsonProperty("startDate") val startDate: Date,
    @JsonProperty("status") val status: Long = 0,
    @JsonProperty("user") val person: Person
)