package ru.newuserkk.volunteers.domain.event

import ru.newuserkk.volunteers.domain.event.model.Event
import ru.newuserkk.volunteers.domain.museum.model.Museum

interface EventRepository {

    suspend fun getAll(): List<Event>

    suspend fun getAllByMuseum(museum: Museum): List<Event>

    suspend fun addEvent(event: Event): Event

}