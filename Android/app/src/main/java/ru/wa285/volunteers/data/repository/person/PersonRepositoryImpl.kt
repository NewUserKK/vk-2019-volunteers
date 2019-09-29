package ru.wa285.volunteers.data.repository.person

import retrofit2.Retrofit
import ru.wa285.volunteers.data.common.exception.BadResponseException
import ru.wa285.volunteers.data.common.exception.IncorrectCredentialsException
import ru.wa285.volunteers.data.net.PersonApiService
import ru.wa285.volunteers.data.net.tryConnect
import ru.wa285.volunteers.data.repository.person.model.PersonWithPassword
import ru.wa285.volunteers.domain.common.OperationResult
import ru.wa285.volunteers.domain.event.model.Event
import ru.wa285.volunteers.domain.person.PersonRepository
import ru.wa285.volunteers.domain.person.model.Person
import ru.wa285.volunteers.domain.person.model.PersonAuthCredentials

class PersonRepositoryImpl(private val retrofit: Retrofit) : PersonRepository {

    private val personApiService: PersonApiService = retrofit.create(PersonApiService::class.java)

    private val eventSubscriptions = mutableSetOf<Event>()

    private var loggedUser: Person? = null

    override suspend fun authorize(credentials: PersonAuthCredentials): OperationResult<Person> {
        return tryConnect {
            val response = personApiService.authorize(credentials).execute()
            if (response.isSuccessful) {
                val person = response.body() ?: error("Person should not be null")
                loggedUser = person
                OperationResult.Success(person)

            } else {
                val t: OperationResult<Person> = if (response.code() == 403) {
                    OperationResult.Failure(IncorrectCredentialsException())
                } else {
                    OperationResult.Failure(BadResponseException(response))
                }
                t
            }
        }
    }

    override suspend fun register(person: PersonWithPassword): OperationResult<Person> {
        return tryConnect<Person> {
            println(person)
            val response = personApiService.register(person).execute()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                val passResponse = personApiService.addPassword(PersonAuthCredentials(person.login, person.password)).execute()
                if (!passResponse.isSuccessful) {
                    return@tryConnect OperationResult.Failure(BadResponseException(passResponse))
                }
                loggedUser = body
                OperationResult.Success(body)
            } else {
                OperationResult.Failure(BadResponseException(response))
            }
        }
    }

    override fun getLoggedUser(): Person? {
        return loggedUser
    }

    override suspend fun getEventSubscriptions(person: Person): OperationResult<List<Event>> {
        return if (eventSubscriptions.isNotEmpty()) {
            OperationResult.Success(eventSubscriptions.toList())
        } else {
            tryConnect<List<Event>> {
                val response = personApiService.getEventSubscriptions(person.id).execute()
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    eventSubscriptions += body
                    OperationResult.Success(body)
                } else {
                    OperationResult.Failure(BadResponseException(response))
                }
            }
        }
    }

    override suspend fun subscribeToEvent(event: Event, person: Person): OperationResult<Unit> {
        return tryConnect<Unit> {
            val response =
                personApiService.subscribeToEvent(eventId = event.id, personId = person.id)
                    .execute()
            if (response.isSuccessful) {
                eventSubscriptions += event
                OperationResult.Success(Unit)
            } else {
                OperationResult.Failure(BadResponseException(response))
            }
        }
    }

    override suspend fun unsubscribeFromEvent(event: Event, person: Person): OperationResult<Unit> {
        return tryConnect<Unit> {
            val response =
                personApiService.unsubscribeFromEvent(eventId = event.id, personId = person.id)
                    .execute()
            if (response.isSuccessful) {
                eventSubscriptions -= event
                OperationResult.Success(Unit)
            } else {
                OperationResult.Failure(BadResponseException(response))
            }
        }
    }

    override suspend fun getRating(person: Person): OperationResult<Long> {
        return tryConnect<Long> {
            val response =
                personApiService.getRating(person.id).execute()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                OperationResult.Success(body)
            } else {
                OperationResult.Failure(BadResponseException(response))
            }
        }
    }
}