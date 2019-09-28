package ru.newuserkk.volunteers.data.net

import retrofit2.http.GET
import ru.newuserkk.volunteers.domain.event.model.Event

interface VolunteersEventApiService {
    @GET("event")
    suspend fun getEvents() : List<Event>
}