package ru.newuserkk.volunteers.data.repository.event

import retrofit2.Retrofit
import ru.newuserkk.volunteers.data.net.VolunteersEventApiService
import ru.newuserkk.volunteers.domain.event.EventRepository
import ru.newuserkk.volunteers.domain.event.model.Event
import ru.newuserkk.volunteers.domain.museum.model.Museum
import ru.newuserkk.volunteers.domain.person.model.Person

class EventRepositoryImpl(private val retrofit: Retrofit) : EventRepository {
    override suspend fun getAll(): List<Event> {
        return retrofit.create(VolunteersEventApiService::class.java).getEvents()
    }

    override suspend fun getAllByMuseum(museum: Museum): List<Event> {
        return listOf()
    }

    override suspend fun addEvent(event: Event): Event {
        return event
    }

    override suspend fun getParticipantsByEvent(event: Event): List<Person> {
        return listOf()
    }

    override suspend fun getFriendsByEvent(event: Event): List<Person> {
        return listOf()
    }
}