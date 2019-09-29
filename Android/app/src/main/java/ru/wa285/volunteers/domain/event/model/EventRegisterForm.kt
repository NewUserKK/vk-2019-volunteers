package ru.wa285.volunteers.domain.event.model

import android.app.Person
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class EventRegisterForm @JsonCreator constructor(
    @JsonProperty("comment") val comment: String,
    @JsonProperty("end_date") val endDate: Date,
    @JsonProperty("event_id") val eventId: Long,
    @JsonProperty("id") val id: Long = 0,
    @JsonProperty("role_id") val roleId: Long,
    @JsonProperty("start_date") val startDate: Date,
    @JsonProperty("status") val status: Long = 0,
    @JsonProperty("user_id") val personId: Long
)