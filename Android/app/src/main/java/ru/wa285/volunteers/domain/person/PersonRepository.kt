package ru.wa285.volunteers.domain.person

import ru.wa285.volunteers.data.repository.person.model.PersonWithPassword
import ru.wa285.volunteers.domain.common.OperationResult
import ru.wa285.volunteers.domain.person.model.Person
import ru.wa285.volunteers.domain.person.model.PersonAuthCredentials

interface PersonRepository {
    suspend fun authorize(credentials: PersonAuthCredentials): OperationResult<Person>

    suspend fun register(person: PersonWithPassword): OperationResult<Person>

    fun getLoggedUser(): Person?

}