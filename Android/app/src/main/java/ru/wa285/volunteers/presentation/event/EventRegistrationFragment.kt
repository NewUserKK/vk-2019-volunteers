package ru.wa285.volunteers.presentation.event


import android.view.View
import kotlinx.android.synthetic.main.fragment_person_authorization.view.*
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance
import ru.wa285.volunteers.R
import ru.wa285.volunteers.domain.person.PersonRepository
import ru.wa285.volunteers.domain.person.model.PersonAuthCredentials
import ru.wa285.volunteers.presentation.common.AbstractFragment

class EventRegistrationFragment : AbstractFragment() {

    override val layoutResId: Int = R.layout.fragment_person_authorization

    private val personRepository: PersonRepository by kodein.instance()

    override fun View.setupFragment() {
        person_authorization_enter.setOnClickListener {
            submitEvent()
        }
    }

    private fun View.submitEvent() {
        launch {
            personRepository.authorize(
                PersonAuthCredentials(
                    login = person_authorization_request_login.text.toString(),
                    password = person_authorization_request_password.text.toString()
                )
            )
        }
    }
}
