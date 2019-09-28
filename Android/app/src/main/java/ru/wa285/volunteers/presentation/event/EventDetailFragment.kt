package ru.wa285.volunteers.presentation.event


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.content_event_detail.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.kodein.di.generic.instance
import ru.wa285.volunteers.R
import ru.wa285.volunteers.domain.common.OperationResult
import ru.wa285.volunteers.domain.event.EventRepository
import ru.wa285.volunteers.domain.event.model.Event
import ru.wa285.volunteers.presentation.common.AbstractFragment


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
        event_detail_description_value.text = event.description
        launch {
            loadMembers()
            loadFriends()
        }

        event_detail_members_container.setOnClickListener {
            navigateToEventParticipants()
        }
    }

    private suspend fun View.loadFriends() {
        val countFriendsResult = withContext(Dispatchers.IO) {
            eventRepository.getFriendsByEvent(event)
        }
        when (countFriendsResult) {
            is OperationResult.Success -> {
                event_detail_friends.text = resources.getQuantityString(
                    R.plurals.numberOfFriends,
                    countFriendsResult.value.size,
                    countFriendsResult.value.size
                )
            }
            is OperationResult.Failure -> {
                Toast.makeText(context, countFriendsResult.error.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private suspend fun View.loadMembers() {
        val countMembersResult = withContext(Dispatchers.IO) {
            eventRepository.getParticipantsByEvent(event)
        }
        when (countMembersResult) {
            is OperationResult.Success -> {
                event_detail_members.text = resources.getQuantityString(
                    R.plurals.numberOfMembers,
                    countMembersResult.value.size,
                    countMembersResult.value.size
                )
            }
            is OperationResult.Failure -> {
                Toast.makeText(context, countMembersResult.error.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun navigateToEventParticipants() {
        val action =
            EventDetailFragmentDirections.actionEventDetailFragmentToEventParticipantFragment(event)
        findNavController().navigate(action)
    }
}
