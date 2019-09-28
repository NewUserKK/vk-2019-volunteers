package ru.wa285.volunteers.data.repository.achievement

import retrofit2.Retrofit
import ru.wa285.volunteers.data.common.exception.BadResponseException
import ru.wa285.volunteers.data.net.VolunteersAchievementApiService
import ru.wa285.volunteers.data.net.VolunteersMuseumApiService
import ru.wa285.volunteers.data.net.toOperationResult
import ru.wa285.volunteers.data.net.tryConnect
import ru.wa285.volunteers.domain.common.OperationResult
import ru.wa285.volunteers.domain.museum.MuseumRepository
import ru.wa285.volunteers.domain.museum.model.Museum
import ru.wa285.volunteers.domain.person.AchievementRepository
import ru.wa285.volunteers.domain.person.model.Achievement
import java.io.IOException

class AchievementRepositoryImpl(private val retrofit: Retrofit) : AchievementRepository {

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