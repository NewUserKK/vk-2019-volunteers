package ru.wa285.volunteers.data.net

import retrofit2.Call
import retrofit2.http.GET
import ru.wa285.volunteers.domain.museum.model.Museum

interface MuseumApiService {
    @GET("museum")
    fun getAll(): Call<List<Museum>>
}