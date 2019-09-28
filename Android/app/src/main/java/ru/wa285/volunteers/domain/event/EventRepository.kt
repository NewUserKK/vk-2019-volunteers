package ru.wa285.volunteers.domain.event

import ru.wa285.volunteers.domain.common.OperationResult
import ru.wa285.volunteers.domain.event.model.Event
import ru.wa285.volunteers.domain.museum.model.Museum
import ru.wa285.volunteers.domain.person.model.Person

interface EventRepository {

    suspend fun getAll(): OperationResult<List<Event>>

    suspend fun getAllByMuseum(museum: Museum): OperationResult<List<Event>>

    suspend fun addEvent(event: Event): OperationResult<Event>

    suspend fun getParticipantsByEvent(event: Event): OperationResult<List<Person>>

    suspend fun getFriendsByEvent(event: Event): OperationResult<List<Person>>
}