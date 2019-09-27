package ru.newuserkk.volunteers.data.event

import ru.newuserkk.volunteers.domain.event.EventRepository
import ru.newuserkk.volunteers.domain.event.model.Event
import ru.newuserkk.volunteers.domain.museum.model.Museum
import ru.newuserkk.volunteers.domain.person.model.Person

class EventRepositoryImpl : EventRepository {
    override fun getAll(): List<Event> {
        return listOf()
    }

    override fun getAllByMuseum(museum: Museum): List<Event> {
        return listOf()
    }

    override fun addEvent(event: Event): Event {
        return event
    }

    override fun getParticipantsByEvent(event: Event): List<Person> {
        return listOf()
    }

    override fun getFriendsByEvent(event: Event): List<Person> {
        return listOf()
    }
}