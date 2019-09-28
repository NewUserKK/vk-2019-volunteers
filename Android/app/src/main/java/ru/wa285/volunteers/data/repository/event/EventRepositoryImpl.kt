package ru.wa285.volunteers.data.repository.event

import retrofit2.Retrofit
import ru.wa285.volunteers.data.net.VolunteersEventApiService
import ru.wa285.volunteers.data.net.toOperationResult
import ru.wa285.volunteers.domain.common.OperationResult
import ru.wa285.volunteers.domain.event.EventRepository
import ru.wa285.volunteers.domain.event.model.Event
import ru.wa285.volunteers.domain.museum.model.Museum
import ru.wa285.volunteers.domain.person.model.Person

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