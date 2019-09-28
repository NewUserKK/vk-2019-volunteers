package ru.wa285.volunteers.data.net

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import ru.wa285.volunteers.domain.event.model.Event
import ru.wa285.volunteers.domain.person.model.Person

interface EventApiService {
    @GET("event")
    fun getEvents(): Call<List<Event>>

    @GET("event/{id}/actual")
    fun getEventsByPerson(@Path("id") personId: Long): Call<List<Event>>

    @GET("event/{id}/events")
    fun getEventsByMuseum(@Path("id") museumId: Long): Call<List<Event>>

    @GET("user/{id}/participants")
    fun getParticipantsByEvent(@Path("id") eventId: Long): Call<List<Person>>

    @GET("user/participatedFriend")
    fun getFriendsByEvent(eventId: Long, userId: Long): Call<List<Person>>
}