package ru.newuserkk.volunteers.domain.event

import ru.newuserkk.volunteers.domain.event.model.Event
import ru.newuserkk.volunteers.domain.museum.model.Museum

interface EventRepository {

    fun getAll(): List<Event>

    fun getAllByMuseum(museum: Museum): List<Event>

    fun addEvent(event: Event): Event

}