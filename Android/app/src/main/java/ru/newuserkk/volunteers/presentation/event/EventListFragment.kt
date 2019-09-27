package ru.newuserkk.volunteers.presentation.event


import android.view.View
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_event_list.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.kodein.di.generic.instance
import ru.newuserkk.volunteers.R
import ru.newuserkk.volunteers.domain.event.EventRepository
import ru.newuserkk.volunteers.domain.event.model.Event
import ru.newuserkk.volunteers.presentation.BottomNavigationHostFragmentDirections
import ru.newuserkk.volunteers.presentation.common.AbstractFragment

class EventListFragment : AbstractFragment() {

    override val layoutResId = R.layout.fragment_event_list

    private val eventRepository: EventRepository by kodein.instance()

    private val eventList = mutableListOf<Event>()
    lateinit var eventAdapter: EventListRecyclerViewAdapter

    override fun View.setupFragment() {
        eventAdapter = EventListRecyclerViewAdapter(eventList).apply {
            onClickListener = {
                navigateToEventDetail(it)
            }
        }
        event_list.adapter = eventAdapter
        loadEvents()
    }

    private fun loadEvents() {
        launch {
            eventList += withContext(Dispatchers.IO) {
                eventRepository.getAll()
            }
            eventAdapter.notifyDataSetChanged()
        }
    }

    private fun navigateToEventDetail(event: Event) {
        val action = BottomNavigationHostFragmentDirections.
            actionBottomNavigationHostFragmentToEventDetailFragment(event)
        requireParentFragment().findNavController().navigate(action)
    }
}
