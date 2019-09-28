package ru.wa285.volunteers.presentation.person


import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_person_registration.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.kodein.di.generic.instance
import ru.wa285.volunteers.R
import ru.wa285.volunteers.data.repository.person.model.PersonWithPassword
import ru.wa285.volunteers.domain.common.OperationResult
import ru.wa285.volunteers.domain.person.PersonRepository
import ru.wa285.volunteers.domain.person.model.Person
import ru.wa285.volunteers.presentation.common.AbstractFragment
import java.text.ParseException
import java.text.SimpleDateFormat


class PersonRegistrationFragment : AbstractFragment() {

    override val layoutResId: Int = R.layout.fragment_person_registration

    private val personRepository: PersonRepository by kodein.instance()

    override fun View.setupFragment() {
        person_registration_commit_button.setOnClickListener {
            val item = constructItem()
            if (item != null) {
                launch {
                    val result = withContext(Dispatchers.IO) {
                        personRepository.register(item)
                    }
                    when (result) {
                        is OperationResult.Success -> navigateBack()
                        is OperationResult.Failure -> Toast.makeText(
                            context,
                            result.error.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            }
        }
    }

    private fun navigateBack() {
        findNavController().popBackStack()
    }

    fun View.constructItem(): PersonWithPassword? {
        val date = try {
            SimpleDateFormat("dd.mm.yyyy", resources.configuration.locales[0])
                .parse(person_registration_edit_birth_date.text.toString())
        } catch (e: ParseException) {
            Toast.makeText(context, "Неправильно указана дата!", Toast.LENGTH_SHORT).show()
            return null
        }
        if (date == null) {
            Toast.makeText(context, "Неправильно указана дата!", Toast.LENGTH_SHORT).show()
            return null
        }

        return PersonWithPassword(
            person = Person(
                name = person_registration_edit_name.text.toString(),
                surname = person_registration_edit_surname.text.toString(),
                patronymic = person_registration_edit_patronymic.text.toString(),
                phone = person_registration_edit_phone.text.toString(),
                email = person_registration_edit_email.text.toString(),
                login = person_registration_edit_login.text.toString(),
                birthday = date,
                avatarUri = null
            ),
            password = person_registration_edit_password.text.toString()
        )
    }

}
