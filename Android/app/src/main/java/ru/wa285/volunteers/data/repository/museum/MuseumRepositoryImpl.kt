package ru.wa285.volunteers.data.repository.museum

import retrofit2.Retrofit
import ru.wa285.volunteers.data.common.exception.BadResponseException
import ru.wa285.volunteers.data.net.VolunteersMuseumApiService
import ru.wa285.volunteers.data.net.toOperationResult
import ru.wa285.volunteers.data.net.tryConnect
import ru.wa285.volunteers.domain.common.OperationResult
import ru.wa285.volunteers.domain.museum.MuseumRepository
import ru.wa285.volunteers.domain.museum.model.Museum
import java.io.IOException

class MuseumRepositoryImpl(private val retrofit: Retrofit) : MuseumRepository {

    private val museumApiService =
        retrofit.create(VolunteersMuseumApiService::class.java)

    override fun getAll(): OperationResult<List<Museum>> {
        return tryConnect {
            museumApiService.getAll().execute().toOperationResult {
                BadResponseException(it)
            }
        }
    }
}