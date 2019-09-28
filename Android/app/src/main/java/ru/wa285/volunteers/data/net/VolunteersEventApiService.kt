package ru.wa285.volunteers.data.net

import retrofit2.Call
import retrofit2.http.GET
import ru.wa285.volunteers.domain.event.model.Event

interface VolunteersEventApiService {
    @GET("event")
    suspend fun getEvents() : Call<List<Event>>
}