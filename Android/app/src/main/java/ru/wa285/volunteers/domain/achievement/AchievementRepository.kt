package ru.wa285.volunteers.domain.achievement

import ru.wa285.volunteers.domain.achievement.model.Achievement
import ru.wa285.volunteers.domain.common.OperationResult
import ru.wa285.volunteers.domain.person.model.Person

interface AchievementRepository {
    suspend fun getAll(person: Person): OperationResult<List<Achievement>>
}