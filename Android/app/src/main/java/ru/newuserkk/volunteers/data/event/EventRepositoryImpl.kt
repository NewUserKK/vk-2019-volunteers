package ru.newuserkk.volunteers.data.event

import ru.newuserkk.volunteers.domain.event.EventRepository
import ru.newuserkk.volunteers.domain.event.model.Event
import ru.newuserkk.volunteers.domain.museum.model.Museum

class EventRepositoryImpl : EventRepository {
    override fun getAll(): List<Event> {
        TODO("not implemented")
    }

    override fun getAllByMuseum(museum: Museum): List<Event> {
        TODO("not implemented")
    }

    override fun addEvent(event: Event): Event {
        TODO("not implemented")
    }
}