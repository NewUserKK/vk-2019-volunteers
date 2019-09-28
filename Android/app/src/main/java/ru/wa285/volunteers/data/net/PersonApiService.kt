package ru.wa285.volunteers.data.net

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import ru.wa285.volunteers.data.repository.person.model.PersonWithPassword
import ru.wa285.volunteers.domain.person.model.Person
import ru.wa285.volunteers.domain.person.model.PersonAuthCredentials

interface PersonApiService {
    @POST("user/auth")
    fun authorize(@Body credentials: PersonAuthCredentials): Call<Person>

    @POST("user")
    fun register(@Body person: PersonWithPassword): Call<Person>
}