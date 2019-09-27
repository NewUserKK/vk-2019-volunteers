package ru.newuserkk.volunteers.presentation.event


import android.os.Bundle
import android.util.EventLog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import ru.newuserkk.volunteers.R
import ru.newuserkk.volunteers.domain.event.model.Event
import ru.newuserkk.volunteers.presentation.common.AbstractFragment


class EventDetailFragment : AbstractFragment() {

    override val layoutResId = R.layout.fragment_event_detail

    val args: EventDetailFragmentArgs by navArgs()
    lateinit var event: Event

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        event = args.event
    }

    override fun View.setupFragment() {

    }
}
