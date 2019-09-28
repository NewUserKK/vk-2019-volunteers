package ru.newuserkk.volunteers.domain.event

import ru.newuserkk.volunteers.domain.event.model.Event
import ru.newuserkk.volunteers.domain.museum.model.Museum
import ru.newuserkk.volunteers.domain.person.model.Person

interface EventRepository {

    suspend fun getAll(): List<Event>

    suspend fun getAllByMuseum(museum: Museum): List<Event>

    suspend fun addEvent(event: Event): Event

    suspend fun getParticipantsByEvent(event: Event): List<Person>

    suspend fun getFriendsByEvent(event: Event): List<Person>
}