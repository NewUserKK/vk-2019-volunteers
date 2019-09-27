package ru.newuserkk.volunteers.presentation

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import ru.newuserkk.volunteers.data.event.EventRepositoryImpl
import ru.newuserkk.volunteers.domain.event.EventRepository

class VolunteersApp : Application() {

    companion object {
        lateinit var kodein: Kodein
    }

    override fun onCreate() {
        super.onCreate()
        kodein = Kodein {
            bind<EventRepository>() with singleton { EventRepositoryImpl() }
        }
    }

}