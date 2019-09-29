package ru.wa285.volunteers.presentation.museum

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_museum_detail.*
import kotlinx.android.synthetic.main.fragment_museum_detail.view.*
import kotlinx.android.synthetic.main.fragment_museum_detail.view.museum_detail_closest_events_list_container
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.kodein.di.generic.instance
import ru.wa285.volunteers.R
import ru.wa285.volunteers.domain.common.OperationResult
import ru.wa285.volunteers.domain.event.EventRepository
import ru.wa285.volunteers.domain.event.model.Event
import ru.wa285.volunteers.domain.museum.model.Museum
import ru.wa285.volunteers.domain.person.PersonRepository
import ru.wa285.volunteers.presentation.common.AbstractFragment
import ru.wa285.volunteers.presentation.common.switchTo
import ru.wa285.volunteers.presentation.common.view.NamePicture
import ru.wa285.volunteers.presentation.event.EventAdapterItem
import ru.wa285.volunteers.presentation.event.EventListRecyclerViewAdapter

class MuseumDetailFragment : AbstractFragment() {

    override val layoutResId: Int = R.layout.fragment_museum_detail

    private val args: MuseumDetailFragmentArgs by navArgs()
    lateinit var museum: Museum

    private val personRepository: PersonRepository by kodein.instance()
    private val eventRepository: EventRepository by kodein.instance()

    private val eventList = mutableListOf<EventAdapterItem>()
    lateinit var eventAdapter: EventListRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        museum = args.museum
    }

    override fun View.setupFragment() {
        museum_detail_name.text = museum.name
        museum_detail_avatar_view.value = NamePicture(museum.name, museum.photo)
        museum_detail_description.text = museum.description

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
        (museum_detail_closest_events_list as RecyclerView).adapter = eventAdapter
        loadEvents()
    }

    private fun loadEvents() {
        launch {
            val result = withContext(Dispatchers.IO) {
                eventRepository.getAllByMuseum(museum)
            }
            when (result) {
                is OperationResult.Success -> {
                    eventList.clear()
                    val logged = personRepository.getLoggedUser()
                    eventList += if (logged != null) {
                        val favourites = (withContext(Dispatchers.IO) {
                            personRepository.getEventSubscriptions(logged)
                        } as OperationResult.Success).value.toSet()
                        result.value.sortedByDescending { it.startDate }
                            .map { EventAdapterItem(it, it in favourites) }
                    } else {
                        result.value.sortedByDescending { it.startDate }
                            .map { EventAdapterItem(it, false) }
                    }
                    if (eventList.isEmpty()) {
                        museum_detail_closest_events_list_container.switchTo(museum_detail_closest_events_list_placeholder)
                    } else {
                        museum_detail_closest_events_list_container.switchTo(museum_detail_closest_events_list)
                    }
                    eventAdapter.notifyDataSetChanged()
                }
                is OperationResult.Failure -> {
                    Toast.makeText(context, result.error.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun navigateToEventDetail(event: Event) {
        val action =
            MuseumDetailFragmentDirections.actionMuseumDetailFragmentToEventDetailFragment(event)
        findNavController().navigate(action)
    }
}
