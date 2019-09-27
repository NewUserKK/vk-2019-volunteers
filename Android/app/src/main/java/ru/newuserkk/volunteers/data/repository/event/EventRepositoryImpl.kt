package ru.newuserkk.volunteers.data.repository.event

import ru.newuserkk.volunteers.domain.event.EventRepository
import ru.newuserkk.volunteers.domain.event.model.Event
import ru.newuserkk.volunteers.domain.museum.model.Museum

class EventRepositoryImpl : EventRepository {
    override suspend fun getAll(): List<Event> {
        return listOf()
    }

    override suspend fun getAllByMuseum(museum: Museum): List<Event> {
        return listOf()
    }

    override suspend fun addEvent(event: Event): Event {
        return event
    }
}