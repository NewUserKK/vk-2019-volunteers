package ru.newuserkk.volunteers.presentation.event

import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_event_list.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.kodein.di.generic.instance
import ru.newuserkk.volunteers.R
import ru.newuserkk.volunteers.domain.event.EventRepository
import ru.newuserkk.volunteers.domain.person.model.Person
import ru.newuserkk.volunteers.presentation.BottomNavigationHostFragmentDirections
import ru.newuserkk.volunteers.presentation.common.AbstractFragment

class EventParticipantFragment : AbstractFragment() {

    override val layoutResId: Int = R.layout.fragment_event_participant

    private val eventRepository: EventRepository by kodein.instance()

    val args: EventDetailFragmentArgs by navArgs()
    private val memberList = mutableListOf<Person>()
    lateinit var eventAdapter: EventParticipantRecyclerViewAdapter

    override fun View.setupFragment() {
        eventAdapter = EventParticipantRecyclerViewAdapter(memberList).apply {
            onClickListener = {
                navigateToEventDetail(it)
            }
        }
        event_list.adapter = eventAdapter
        loadEvents()
    }

    private fun loadEvents() {
        launch {
            memberList += withContext(Dispatchers.IO) {
                eventRepository.getParticipantsByEvent(args.event)
            }
            eventAdapter.notifyDataSetChanged()
        }
    }

    private fun navigateToEventDetail(person: Person) {
        val action = BottomNavigationHostFragmentDirections.
            actionBottomNavigationHostFragmentToEventDetailFragment(args.event)
        requireParentFragment().findNavController().navigate(action)
    }
}