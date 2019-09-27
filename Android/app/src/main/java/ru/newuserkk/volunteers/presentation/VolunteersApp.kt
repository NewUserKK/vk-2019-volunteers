package ru.newuserkk.volunteers.presentation

import android.app.Application
import org.kodein.di.Kodein

class VolunteersApp : Application() {

    companion object {
        lateinit var kodein: Kodein
    }

    override fun onCreate() {
        super.onCreate()
        kodein = Kodein {

        }
    }

}