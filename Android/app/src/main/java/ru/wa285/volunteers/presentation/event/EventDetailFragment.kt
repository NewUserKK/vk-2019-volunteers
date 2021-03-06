package ru.wa285.volunteers.presentation.event


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.content_event_detail.view.*
import kotlinx.android.synthetic.main.fragment_event_detail.view.*
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
import ru.wa285.volunteers.presentation.common.AbstractFragment
import ru.wa285.volunteers.presentation.common.hide
import ru.wa285.volunteers.presentation.common.show


class EventDetailFragment : AbstractFragment() {

    override val layoutResId = R.layout.fragment_event_detail

    private val args: EventDetailFragmentArgs by navArgs()
    private val eventRepository: EventRepository by kodein.instance()
    private val personRepository: PersonRepository by kodein.instance()
    lateinit var event: Event

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        event = args.event
    }

    override fun View.setupFragment() {
        event_detail_name.text = event.title
        event_detail_description_value.text = event.description
        Picasso.get()
            .load(event.photoLink)
            .into(event_detail_image, object : Callback {
                override fun onSuccess() {
                    event_detail_image.show()
                }

                override fun onError(e: Exception?) {
                    event_detail_image.hide()
                }
            })
        val loggedUser = personRepository.getLoggedUser()
        launch {
            loadMembers()
            if (loggedUser != null) {
                loadFriends(loggedUser)
            }
        }

        if (loggedUser == null) {
            event_detail_sign_up.setBackgroundColor(resources.getColor(R.color.gray))
            event_detail_sign_up.text = "Нужна авторизация"
        } else {
            if (loggedUser.rating < event.requiredRating) {
                event_detail_sign_up.setBackgroundColor(resources.getColor(R.color.gray))
                event_detail_sign_up.text = "Не хватает рейтинга"
            }
            event_detail_sign_up.setOnClickListener {
                navigateToEventRegistration(event)
            }
        }

        event_detail_members_container.setOnClickListener {
            navigateToEventParticipants()
        }

        event_detail_rating_value.text = event.requiredRating.toString()
    }

    private fun navigateToEventRegistration(event: Event) {
        val action = EventDetailFragmentDirections.actionEventDetailFragmentToEventRegistrationFragment(event)
        findNavController().navigate(action)
    }

    private suspend fun View.loadFriends(loggedUser: Person) {
        val countFriendsResult = withContext(Dispatchers.IO) {
            eventRepository.getFriendsByEvent(event, loggedUser)
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
