package ru.wa285.volunteers.data.net

import retrofit2.Call
import retrofit2.http.GET
import ru.wa285.volunteers.domain.event.model.Event

interface EventApiService {
    @GET("event")
    fun getEvents() : Call<List<Event>>
}