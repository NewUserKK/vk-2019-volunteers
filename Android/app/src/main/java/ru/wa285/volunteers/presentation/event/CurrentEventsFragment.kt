package ru.wa285.volunteers.presentation.event

import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_current_events.*
import kotlinx.android.synthetic.main.fragment_current_events.view.*
import kotlinx.android.synthetic.main.fragment_current_events.view.current_events_container
import kotlinx.android.synthetic.main.fragment_event_list.*
import kotlinx.android.synthetic.main.fragment_event_list.view.*
import kotlinx.android.synthetic.main.fragment_event_list.view.event_list_container
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.kodein.di.generic.instance
import ru.wa285.volunteers.R
import ru.wa285.volunteers.domain.common.OperationResult
import ru.wa285.volunteers.domain.event.EventRepository
import ru.wa285.volunteers.domain.event.model.Event
import ru.wa285.volunteers.domain.person.PersonRepository
import ru.wa285.volunteers.domain.person.model.Person
import ru.wa285.volunteers.presentation.BottomNavFragment
import ru.wa285.volunteers.presentation.BottomNavigationHostFragmentDirections
import ru.wa285.volunteers.presentation.common.AbstractFragment
import ru.wa285.volunteers.presentation.common.switchTo

class CurrentEventsFragment : AbstractFragment(), BottomNavFragment {

    override val layoutResId: Int = R.layout.fragment_current_events

    private val eventRepository: EventRepository by kodein.instance()
    private val personRepository: PersonRepository by kodein.instance()

    private val eventList = mutableListOf<EventAdapterItem>()
    private lateinit var eventAdapter: EventListRecyclerViewAdapter

    override fun onResume() {
        super.onResume()
        if (::eventAdapter.isInitialized) {
            eventAdapter.notifyDataSetChanged()
        }
    }

    override fun View.setupFragment() {
        eventAdapter = EventListRecyclerViewAdapter(eventList).apply {
            onClickListener = {
                navigateToEventDetail(it)
            }
            onFavouriteClickListener = { event, fav ->
                launch(Dispatchers.IO) {
                    if (fav) {
                        personRepository.subscribeToEvent(event, personRepository.getLoggedUser()!!)
                    } else {
                        personRepository.unsubscribeFromEvent(event, personRepository.getLoggedUser()!!)
                    }
                }
            }
        }
        current_events_list.adapter = eventAdapter
        val loggedUser = personRepository.getLoggedUser()
        if (loggedUser != null) {
            loadEvents(loggedUser)
        }
    }

    private fun loadEvents(loggedUser: Person) {
        launch {
            val result = withContext(Dispatchers.IO) {
                personRepository.getEventSubscriptions(loggedUser)
            }
            when (result) {
                is OperationResult.Success -> {
                    eventList.clear()
                    eventList +=
                        result.value
                            .sortedByDescending { it.dateStart }
                            .map { EventAdapterItem(it, true) }
                    eventAdapter.notifyDataSetChanged()
                    if (eventList.isEmpty()) {
                        current_events_container.switchTo(current_events_placeholder)
                    } else {
                        current_events_container.switchTo(current_events_list)
                    }
                }
                is OperationResult.Failure -> {
                    Toast.makeText(context, result.error.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun navigateToEventDetail(event: Event) {
        val action = BottomNavigationHostFragmentDirections.
            actionBottomNavigationHostFragmentToEventDetailFragment(event)
        requireParentFragment().findNavController().navigate(action)
    }

    override fun updateAdapters() {
        if (::eventAdapter.isInitialized) {
            eventAdapter.notifyDataSetChanged()
        }
    }
}
