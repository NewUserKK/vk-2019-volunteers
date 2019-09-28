package ru.wa285.volunteers.presentation.event.participant

import android.util.EventLog
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_event_participant_list.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.withTestContext
import kotlinx.coroutines.withContext
import org.kodein.di.generic.instance
import ru.wa285.volunteers.R
import ru.wa285.volunteers.domain.common.OperationResult
import ru.wa285.volunteers.domain.event.EventRepository
import ru.wa285.volunteers.domain.event.model.Event
import ru.wa285.volunteers.domain.person.PersonRepository
import ru.wa285.volunteers.domain.person.model.Person
import ru.wa285.volunteers.presentation.common.AbstractFragment

class EventParticipantListFragment : AbstractFragment() {

    override val layoutResId: Int = R.layout.fragment_event_participant_list

    val event: Event
        get() = (requireParentFragment() as EventParticipantFragment).event

    private val eventRepository: EventRepository by kodein.instance()

    private val participantList = mutableListOf<Person>()
    lateinit var participantAdapter: EventParticipantRecyclerViewAdapter

    override fun View.setupFragment() {
        participantAdapter = EventParticipantRecyclerViewAdapter(participantList).apply {
            onClickListener = {
            }
        }
        event_participant_list.adapter = participantAdapter
        launch {
            val result = withContext(Dispatchers.IO) {
                eventRepository.getParticipantsByEvent(event)
            }
            when (result) {
                is OperationResult.Success -> {
                    participantList.clear()
                    participantList += result.value
                }
                is OperationResult.Failure -> {
                    Toast.makeText(context, result.error.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}
