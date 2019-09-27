package ru.newuserkk.volunteers.presentation.event


import android.os.Bundle
import android.util.EventLog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.content_event_detail.view.*
import org.kodein.di.generic.instance
import ru.newuserkk.volunteers.R
import ru.newuserkk.volunteers.domain.event.EventRepository
import ru.newuserkk.volunteers.domain.event.model.Event
import ru.newuserkk.volunteers.presentation.common.AbstractFragment


class EventDetailFragment : AbstractFragment() {

    override val layoutResId = R.layout.fragment_event_detail

    val args: EventDetailFragmentArgs by navArgs()
    private val eventRepository: EventRepository by kodein.instance()
    lateinit var event: Event

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        event = args.event
    }

    override fun View.setupFragment() {
        event_detail_name.text = event.name
        event_detail_description.text = event.description
        val countMembers = eventRepository.getParticipantsByEvent(event).size
        event_detail_common_members.text = resources.getQuantityString(R.plurals.numberOfMembers, countMembers, countMembers)
        val countFriends = eventRepository.getFriendsByEvent(event).size
        event_detail_friends.text = resources.getQuantityString(R.plurals.numberOfFriends, countFriends, countFriends)
        event_detail_description.text = event.description
    }
}
