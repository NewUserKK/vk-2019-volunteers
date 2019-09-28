package ru.wa285.volunteers.data.repository.achievement

import retrofit2.Retrofit
import ru.wa285.volunteers.data.net.VolunteersAchievementApiService
import ru.wa285.volunteers.domain.achievement.AchievementRepository

class AchievementRepositoryImpl(private val retrofit: Retrofit) :
    AchievementRepository {

    private val achievementApiService =
        retrofit.create(VolunteersAchievementApiService::class.java)

//    override fun getAllByPerson(): OperationResult<List<Achievement>> {
//        return tryConnect {
//            achievementApiService.getAllByPerson().execute().toOperationResult {
//                BadResponseException(it)
//            }
//        }
//    }
}