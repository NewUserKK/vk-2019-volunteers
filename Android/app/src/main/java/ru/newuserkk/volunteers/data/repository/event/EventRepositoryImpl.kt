package ru.newuserkk.volunteers.data.repository.event

import retrofit2.Retrofit
import ru.newuserkk.volunteers.data.net.VolunteersEventApiService
import ru.newuserkk.volunteers.data.net.toOperationResult
import ru.newuserkk.volunteers.domain.common.OperationResult
import ru.newuserkk.volunteers.domain.event.EventRepository
import ru.newuserkk.volunteers.domain.event.model.Event
import ru.newuserkk.volunteers.domain.museum.model.Museum
import ru.newuserkk.volunteers.domain.person.model.Person

class EventRepositoryImpl(private val retrofit: Retrofit) : EventRepository {

    private val retrofitService: VolunteersEventApiService =
        retrofit.create(VolunteersEventApiService::class.java)

    override suspend fun getAll(): OperationResult<List<Event>> {
        val response = retrofitService.getEvents().execute()
        return response.toOperationResult {
            error("No internet connection")
        }
    }

    override suspend fun getAllByMuseum(museum: Museum): OperationResult<List<Event>> {
        return listOf<Event>().toOperationResult()
    }

    override suspend fun addEvent(event: Event): OperationResult<Event> {
        return event.toOperationResult()
    }

    override suspend fun getParticipantsByEvent(event: Event): OperationResult<List<Person>> {
        return listOf<Person>().toOperationResult()
    }

    override suspend fun getFriendsByEvent(event: Event): OperationResult<List<Person>> {
        return listOf<Person>().toOperationResult()
    }
}