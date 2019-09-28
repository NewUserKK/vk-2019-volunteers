package ru.wa285.volunteers.data.repository.achievement

import retrofit2.Retrofit
import ru.wa285.volunteers.data.common.exception.BadResponseException
import ru.wa285.volunteers.data.net.VolunteersAchievementApiService
import ru.wa285.volunteers.data.net.toOperationResult
import ru.wa285.volunteers.data.net.tryConnect
import ru.wa285.volunteers.domain.achievement.AchievementRepository
import ru.wa285.volunteers.domain.achievement.model.Achievement
import ru.wa285.volunteers.domain.common.OperationResult
import ru.wa285.volunteers.domain.person.model.Person

class AchievementRepositoryImpl(private val retrofit: Retrofit) :
    AchievementRepository {

    private val achievementApiService =
        retrofit.create(VolunteersAchievementApiService::class.java)

    override suspend fun getAll(person: Person): OperationResult<List<Achievement>> {
        return tryConnect {
            achievementApiService.getAll(person.id).execute().toOperationResult {
                BadResponseException(it)
            }
        }
    }
}