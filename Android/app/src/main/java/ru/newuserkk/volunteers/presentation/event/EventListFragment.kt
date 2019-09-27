package ru.newuserkk.volunteers.presentation.event


import android.view.View
import kotlinx.android.synthetic.main.fragment_event_list.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.kodein.di.generic.instance
import ru.newuserkk.volunteers.R
import ru.newuserkk.volunteers.domain.event.EventRepository
import ru.newuserkk.volunteers.domain.event.model.Event
import ru.newuserkk.volunteers.presentation.common.AbstractFragment

class EventListFragment : AbstractFragment() {

    override val layoutResId = R.layout.fragment_event_list

    private val eventRepository: EventRepository by kodein.instance()

    val eventList = mutableListOf<Event>()
    lateinit var eventAdapter: EventListRecyclerViewAdapter

    override fun View.setupFragment() {
        eventAdapter = EventListRecyclerViewAdapter(eventList)
        launch {
            eventList += withContext(Dispatchers.IO) {
                eventRepository.getAll()
            }
            eventAdapter.notifyDataSetChanged()
        }
    }
}
