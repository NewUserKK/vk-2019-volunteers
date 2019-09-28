package ru.wa285.volunteers.presentation.event

import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_event_list.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.kodein.di.generic.instance
import ru.wa285.volunteers.R
import ru.wa285.volunteers.domain.common.OperationResult
import ru.wa285.volunteers.domain.event.EventRepository
import ru.wa285.volunteers.domain.event.model.Event
import ru.wa285.volunteers.presentation.BottomNavigationHostFragmentDirections
import ru.wa285.volunteers.presentation.common.AbstractFragment

class CurrentEventsFragment : AbstractFragment() {

    override val layoutResId: Int = R.layout.fragment_current_events

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
            val result = withContext(Dispatchers.IO) {
                eventRepository.
            }
            when (result) {
                is OperationResult.Success -> {
                    eventList += result.value
                    eventAdapter.notifyDataSetChanged()
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
}
