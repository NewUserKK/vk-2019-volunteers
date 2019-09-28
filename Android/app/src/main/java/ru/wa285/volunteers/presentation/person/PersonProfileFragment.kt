package ru.wa285.volunteers.presentation.person

import android.view.View
import kotlinx.android.synthetic.main.fragment_person_authorization.view.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.kodein.di.generic.instance
import ru.wa285.volunteers.R
import ru.wa285.volunteers.data.common.exception.IncorrectCredentialsException
import ru.wa285.volunteers.domain.common.OperationResult
import ru.wa285.volunteers.domain.person.PersonRepository
import ru.wa285.volunteers.domain.person.model.Person
import ru.wa285.volunteers.domain.person.model.PersonAuthCredentials
import ru.wa285.volunteers.presentation.common.AbstractFragment
import ru.wa285.volunteers.presentation.common.show
import ru.wa285.volunteers.presentation.common.switchTo


class ProfileFragment : AbstractFragment() {

    override val layoutResId: Int = R.layout.fragment_profile

    private val personRepository: PersonRepository by instance()

    override fun View.setupFragment() {
        val loggedUser = personRepository.getLoggedUser()
        if (loggedUser != null) {
            setupProfile(loggedUser)
        } else {
            setupAuthFragment()
        }
    }

    private fun View.setupProfile(person: Person) {
        profile_container.switchTo(profile_content)
    }

    private fun View.setupAuthFragment() {
        profile_container.switchTo(profile_auth)
        person_authorization_enter.setOnClickListener {
            authorizePerson()
        }
    }

    private fun View.authorizePerson() {
        launch {
            val authorized = withContext(Dispatchers.IO) {
                personRepository.authorize(
                    PersonAuthCredentials(
                        login = person_authorization_request_login.text.toString(),
                        password = person_authorization_request_password.text.toString()
                    )
                )
            }
            when (authorized) {
                is OperationResult.Success -> setupProfile(authorized.value)
                is OperationResult.Failure -> when (authorized.error) {
                    is IncorrectCredentialsException -> {
                        person_authorization_error.show()
                    }
                    else -> {
                    }
                }.also { authorized.error.printStackTrace() }
            }
        }
    }
}
