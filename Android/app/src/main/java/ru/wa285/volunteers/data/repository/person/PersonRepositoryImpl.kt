package ru.wa285.volunteers.data.repository.person

import retrofit2.Retrofit
import ru.wa285.volunteers.data.common.exception.BadResponseException
import ru.wa285.volunteers.data.common.exception.IncorrectCredentialsException
import ru.wa285.volunteers.data.net.PersonApiService
import ru.wa285.volunteers.data.net.toOperationResult
import ru.wa285.volunteers.data.net.tryConnect
import ru.wa285.volunteers.data.repository.person.model.PersonWithPassword
import ru.wa285.volunteers.domain.common.OperationResult
import ru.wa285.volunteers.domain.person.PersonRepository
import ru.wa285.volunteers.domain.person.model.Person
import ru.wa285.volunteers.domain.person.model.PersonAuthCredentials

class PersonRepositoryImpl(private val retrofit: Retrofit) : PersonRepository {

    private val personApiService: PersonApiService = retrofit.create(PersonApiService::class.java)

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
}