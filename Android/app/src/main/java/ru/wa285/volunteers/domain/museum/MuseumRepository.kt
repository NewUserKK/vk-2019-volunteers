package ru.wa285.volunteers.domain.museum

import ru.wa285.volunteers.domain.common.OperationResult
import ru.wa285.volunteers.domain.museum.model.Museum
import ru.wa285.volunteers.domain.person.model.Person

interface MuseumRepository {
    suspend fun getAll(): OperationResult<List<Museum>>

    suspend fun getAllByPerson(person: Person): OperationResult<List<Museum>>
}