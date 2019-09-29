package ru.wa285.volunteers.data.net

import retrofit2.Call
import retrofit2.http.*
import ru.wa285.volunteers.data.repository.person.model.PersonWithPassword
import ru.wa285.volunteers.domain.event.model.Event
import ru.wa285.volunteers.domain.person.model.Person
import ru.wa285.volunteers.domain.person.model.PersonAuthCredentials

interface PersonApiService {
    @POST("user/auth")
    fun authorize(@Body credentials: PersonAuthCredentials): Call<Person>

    @POST("user")
    fun register(@Body person: PersonWithPassword): Call<Person>

    @POST("user/register")
    fun addPassword(@Body credentials: PersonAuthCredentials): Call<Person>

    @GET("event/{id}/actual")
    fun getEventSubscriptions(@Path("id") personId: Long): Call<List<Event>>

    @POST("event/subscribe")
    fun subscribeToEvent(@Query("eventId") eventId: Long, @Query("userId") personId: Long): Call<Unit>

    @DELETE("event/unsubscribe")
    fun unsubscribeFromEvent(@Query("eventId") eventId: Long, @Query("userId") personId: Long): Call<Unit>

    @GET("user/{id}/rating")
    fun getRating(@Path("id") personId: Long) : Call<Long>
}