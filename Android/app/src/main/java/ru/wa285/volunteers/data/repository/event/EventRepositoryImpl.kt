package ru.wa285.volunteers.data.repository.event

import retrofit2.Retrofit
import ru.wa285.volunteers.data.common.exception.BadResponseException
import ru.wa285.volunteers.data.net.EventApiService
import ru.wa285.volunteers.data.net.toOperationResult
import ru.wa285.volunteers.data.net.tryConnect
import ru.wa285.volunteers.domain.common.OperationResult
import ru.wa285.volunteers.domain.event.EventRepository
import ru.wa285.volunteers.domain.event.model.Event
import ru.wa285.volunteers.domain.museum.model.Museum
import ru.wa285.volunteers.domain.person.model.Person

class EventRepositoryImpl(private val retrofit: Retrofit) : EventRepository {

    private val retrofitService: EventApiService =
        retrofit.create(EventApiService::class.java)

    override suspend fun getAll(): OperationResult<List<Event>> {
        return tryConnect {
            val response = retrofitService.getEvents().execute()
            response.toOperationResult {
                BadResponseException(it)
            }
        }
    }

    override suspend fun getAllByMuseum(museum: Museum): OperationResult<List<Event>> {
        return tryConnect {
            val response = retrofitService.getEventsByMuseum(museum.id).execute()
            response.toOperationResult {
                BadResponseException(it)
            }
        }
    }

    override suspend fun getAllByPerson(person: Person): OperationResult<List<Event>> {
        return tryConnect {
            val response = retrofitService.getEventsByPerson(person.id).execute()
            response.toOperationResult {
                BadResponseException(it)
            }
        }
    }

    override suspend fun addEvent(event: Event): OperationResult<Event> {
        return event.toOperationResult()
    }

    override suspend fun getParticipantsByEvent(event: Event): OperationResult<List<Person>> {
        return tryConnect {
            val response = retrofitService.getParticipantsByEvent(event.id).execute()
            response.toOperationResult {
                BadResponseException(it)
            }
        }
    }

    override suspend fun getFriendsByEvent(event: Event, person: Person): OperationResult<List<Person>> {
        return tryConnect {
            val response = retrofitService.getFriendsByEvent(event.id, person.id).execute()
            response.toOperationResult {
                BadResponseException(it)
            }
        }
    }
}