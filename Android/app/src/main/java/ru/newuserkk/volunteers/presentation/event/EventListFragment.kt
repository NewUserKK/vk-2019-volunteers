package ru.newuserkk.volunteers.presentation.event


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.kodein.di.generic.instance
import ru.newuserkk.volunteers.R
import ru.newuserkk.volunteers.domain.event.EventRepository
import ru.newuserkk.volunteers.presentation.VolunteersApp.Companion.kodein
import ru.newuserkk.volunteers.presentation.common.AbstractFragment

class EventListFragment : AbstractFragment() {

    override val layoutResId = R.layout.fragment_event_list

    val eventRepository: EventRepository by kodein.instance()

    override fun View.setupFragment() {
        TODO("not implemented")
    }
}
