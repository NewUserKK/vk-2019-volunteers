package ru.newuserkk.volunteers.presentation

import android.app.Application
import android.content.res.Resources
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import ru.newuserkk.volunteers.data.repository.event.EventRepositoryImpl
import ru.newuserkk.volunteers.domain.event.EventRepository

class VolunteersApp : Application() {

    companion object {
        lateinit var kodein: Kodein
        lateinit var retrofit: Retrofit
    }

    override fun onCreate() {
        super.onCreate()
        val jacksonMapper = ObjectMapper()
        jacksonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        retrofit = Retrofit.Builder()
            .baseUrl("http://demo135.foxtrot.vkhackathon.com:8080/api/v1/")
            .addConverterFactory(JacksonConverterFactory.create(jacksonMapper))
            .build()
        kodein = Kodein {
            bind<Resources>() with provider { resources }
            bind<Retrofit>() with instance(retrofit)
            bind<EventRepository>() with singleton { EventRepositoryImpl(instance()) }
        }
    }
}