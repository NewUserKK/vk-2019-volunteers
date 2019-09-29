package ru.wa285.volunteers.data.net

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import ru.wa285.volunteers.domain.achievement.model.Achievement
import ru.wa285.volunteers.domain.museum.model.Museum

interface VolunteersAchievementApiService {
    @GET("achievement/{userId}")
    fun getAll(@Path("userId") userId: Long): Call<List<Achievement>>
}