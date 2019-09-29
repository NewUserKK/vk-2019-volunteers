package ru.wa285.volunteers.presentation.person

import android.annotation.SuppressLint
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.content_profile.view.*
import kotlinx.android.synthetic.main.fragment_person_authorization.view.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.kodein.di.generic.instance
import ru.wa285.volunteers.R
import ru.wa285.volunteers.data.common.exception.BadResponseException
import ru.wa285.volunteers.data.common.exception.IncorrectCredentialsException
import ru.wa285.volunteers.domain.achievement.AchievementRepository
import ru.wa285.volunteers.domain.achievement.model.Achievement
import ru.wa285.volunteers.domain.common.OperationResult
import ru.wa285.volunteers.domain.person.PersonRepository
import ru.wa285.volunteers.domain.person.model.Person
import ru.wa285.volunteers.domain.person.model.PersonAuthCredentials
import ru.wa285.volunteers.domain.person.model.fullName
import ru.wa285.volunteers.presentation.BottomNavFragment
import ru.wa285.volunteers.presentation.BottomNavigationHostFragmentDirections
import ru.wa285.volunteers.presentation.common.*
import ru.wa285.volunteers.presentation.common.view.NamePicture


class ProfileFragment : AbstractFragment(), BottomNavFragment {

    override val layoutResId: Int = R.layout.fragment_profile

    private val personRepository: PersonRepository by instance()
    private val achievementsRepository: AchievementRepository by instance()

    private val achievementsList = mutableListOf<Achievement>()
    lateinit var achievementRecyclerViewAdapter: ProfileAchievementListRecyclerViewAdapter

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
        achievementRecyclerViewAdapter = ProfileAchievementListRecyclerViewAdapter(achievementsList).apply {
            onClickListener = {
                //TODO: показывать какую-то плашку с инфой об ачивке
                Toast.makeText(context, it.name + "\n" + it.description, Toast.LENGTH_SHORT).show()
            }
        }
        profile_achievements_list.adapter = achievementRecyclerViewAdapter
        fillFields(person)
        fillAchievements(person)
        profile_favourite_museum_list_title.setOnClickListener {
            requireParentFragment().findNavController().navigate(
                BottomNavigationHostFragmentDirections
                    .actionBottomNavigationHostFragmentToProfileFavoriteMuseumListFragment(person)
            )
        }
    }

    private fun View.setupAuthFragment() {
        profile_container.switchTo(profile_auth)
        person_authorization_enter.setOnClickListener {
            authorizePerson()
        }
        person_authorization_register_button.setOnClickListener {
            navigateToRegistration()
        }
    }

    private fun navigateToRegistration() {
        val action = BottomNavigationHostFragmentDirections.actionBottomNavigationHostFragmentToPersonRegistrationFragment()
        requireParentFragment().findNavController().navigate(action)
    }

    @SuppressLint("SetTextI18n")
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
                        person_authorization_error.hide()
                        person_authorization_error.show()
                    }
                    is BadResponseException -> {
                        person_authorization_error.text = "Что-то пошло не так. Код ошибки: ${authorized.error.code}"
                    }
                    else -> {}
                }.also { authorized.error.printStackTrace() }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun View.fillFields(person: Person) {
        profile_fullname.text = person.fullName()
        profile_avatar_view.value = NamePicture(person.name, person.avatarUri)
        profile_login.text = "@ ${person.login}"
        profile_birthday.text = "Дата рождения: ${person.birthday.toLocalizedString()}"
        val email = person.email
        if (email != null) {
            profile_email.text = "E-mail: $email"
        }
        val phone = person.phone
        if (phone != null) {
            profile_phone.text = "Номер телефона: $phone"
        }
        launch {
            val rating = withContext(Dispatchers.IO) {
                personRepository.getRating(person)
            }
            when (rating) {
                is OperationResult.Success -> {
                    profile_rating.text = "Текущий рейтинг: ${rating.value}"
                }
                is OperationResult.Failure -> {
                    Toast.makeText(context, rating.error.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun fillAchievements(person: Person) {
        launch {
            val result = withContext(Dispatchers.IO) {
                achievementsRepository.getAll(person)
            }
            when (result) {
                is OperationResult.Success -> {
                    achievementsList.clear()
                    achievementsList += result.value
                    achievementRecyclerViewAdapter.notifyDataSetChanged()
                }
                is OperationResult.Failure -> {
                    Toast.makeText(context, result.error.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun updateAdapters() {
        if (::achievementRecyclerViewAdapter.isInitialized) {
            achievementRecyclerViewAdapter.notifyDataSetChanged()
        }
    }
}
