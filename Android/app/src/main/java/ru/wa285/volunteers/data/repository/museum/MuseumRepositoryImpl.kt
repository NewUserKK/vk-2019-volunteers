package ru.wa285.volunteers.data.repository.museum

import retrofit2.Retrofit
import ru.wa285.volunteers.data.common.exception.BadResponseException
import ru.wa285.volunteers.data.net.MuseumApiService
import ru.wa285.volunteers.data.net.toOperationResult
import ru.wa285.volunteers.data.net.tryConnect
import ru.wa285.volunteers.domain.common.OperationResult
import ru.wa285.volunteers.domain.museum.MuseumRepository
import ru.wa285.volunteers.domain.museum.model.Museum
import ru.wa285.volunteers.domain.person.model.Person

class MuseumRepositoryImpl(private val retrofit: Retrofit) : MuseumRepository {

    private val museumApiService =
        retrofit.create(MuseumApiService::class.java)

    override suspend fun getAll(): OperationResult<List<Museum>> {
        return tryConnect {
            museumApiService.getAll().execute().toOperationResult {
                BadResponseException(it)
            }
        }
    }

    override suspend fun getAllByPerson(person: Person): OperationResult<List<Museum>> {
        return tryConnect {
            museumApiService.getAllByPerson(person.id).execute().toOperationResult {
                BadResponseException(it)
            }
        }
    }
}