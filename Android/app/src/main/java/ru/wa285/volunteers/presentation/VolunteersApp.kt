package ru.wa285.volunteers.presentation

import android.app.Application
import android.content.res.Resources
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.util.StdDateFormat
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import ru.wa285.volunteers.data.repository.event.EventRepositoryImpl
import ru.wa285.volunteers.data.repository.museum.MuseumRepositoryImpl
import ru.wa285.volunteers.data.repository.person.PersonRepositoryImpl
import ru.wa285.volunteers.domain.event.EventRepository
import ru.wa285.volunteers.domain.museum.MuseumRepository
import ru.wa285.volunteers.domain.museum.model.Museum
import ru.wa285.volunteers.domain.person.PersonRepository

class VolunteersApp : Application() {

    companion object {
        lateinit var kodein: Kodein
        lateinit var retrofit: Retrofit
    }

    override fun onCreate() {
        super.onCreate()
        val jacksonMapper = ObjectMapper()
        jacksonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        jacksonMapper.dateFormat = StdDateFormat().withColonInTimeZone(true)
        retrofit = Retrofit.Builder()
            .baseUrl("http://demo135.foxtrot.vkhackathon.com:8080/api/v1/")
            .addConverterFactory(JacksonConverterFactory.create(jacksonMapper))
            .build()
        kodein = Kodein {
            bind<Resources>() with provider { resources }
            bind<Retrofit>() with instance(retrofit)
            bind<EventRepository>() with singleton { EventRepositoryImpl(instance()) }
            bind<MuseumRepository>() with singleton { MuseumRepositoryImpl(instance()) }
            bind<PersonRepository>() with singleton { PersonRepositoryImpl(instance()) }
        }
    }
}