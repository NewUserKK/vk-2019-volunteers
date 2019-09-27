package ru.newuserkk.volunteers.data.repository.event

import retrofit2.Retrofit
import retrofit2.create
import ru.newuserkk.volunteers.data.net.VolunteersEventApiService
import ru.newuserkk.volunteers.domain.event.EventRepository
import ru.newuserkk.volunteers.domain.event.model.Event
import ru.newuserkk.volunteers.domain.museum.model.Museum

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
}