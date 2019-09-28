package ru.wa285.volunteers.data.net

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import ru.wa285.volunteers.domain.museum.model.Museum

interface MuseumApiService {
    @GET("museum")
    fun getAll(): Call<List<Museum>>

    @GET("museum/{id}/favourites")
    fun getAllByPerson(@Path("id") personId: Long): Call<List<Museum>>
}