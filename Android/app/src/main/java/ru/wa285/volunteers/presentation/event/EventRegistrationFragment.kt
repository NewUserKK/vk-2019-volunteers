package ru.wa285.volunteers.presentation.event


import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_event_registration.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.kodein.di.generic.instance
import ru.wa285.volunteers.R
import ru.wa285.volunteers.domain.common.OperationResult
import ru.wa285.volunteers.domain.event.EventRepository
import ru.wa285.volunteers.domain.event.model.Event
import ru.wa285.volunteers.domain.event.model.EventRegisterForm
import ru.wa285.volunteers.domain.person.PersonRepository
import ru.wa285.volunteers.domain.role.model.Role
import ru.wa285.volunteers.presentation.VolunteersApp
import ru.wa285.volunteers.presentation.common.AbstractFragment
import ru.wa285.volunteers.presentation.common.dateToLocalizedString
import java.util.*

class EventRegistrationFragment : AbstractFragment() {

    override val layoutResId: Int = R.layout.fragment_event_registration

    private val args: EventRegistrationFragmentArgs by navArgs()
    lateinit var event: Event

    private val personRepository: PersonRepository by kodein.instance()
    private val eventRepository: EventRepository by kodein.instance()

    private var startDate: Calendar? = null
    private var endDate: Calendar? = null

    private var roleAdapter: ArrayAdapter<Role>? = null
    private val roleList = mutableListOf<Role>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        event = args.event
    }

    override fun View.setupFragment() {
        event_registration_start_date_picker.setOnClickListener {
            println("fgdfgdfgfdcgdcfg")
            val datePickerListener =
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    setStartDate(year, month, dayOfMonth)
                    event_registration_start_date_value.text =
                        dateToLocalizedString(startDate!!, VolunteersApp.locale)
                }
            showDatePickerDialog(startDate, datePickerListener)
        }
        event_registration_end_date_picker.setOnClickListener {
            val datePickerListener =
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    setEndDate(year, month, dayOfMonth)
                    event_registration_end_date_value.text =
                        dateToLocalizedString(endDate!!, VolunteersApp.locale)
                }
            showDatePickerDialog(endDate, datePickerListener)
        }
        event_registration_submit.setOnClickListener {
            submitEvent()
        }
        roleAdapter = ArrayAdapter(
            context,
            android.R.layout.simple_spinner_dropdown_item,
            roleList
        )
        event_registration_role_spinner.adapter = roleAdapter

            launch {
            val result = withContext(Dispatchers.IO) {
                eventRepository.getAllRoles(event)
            }
            when (result) {
                is OperationResult.Success -> {
                    roleList.clear()
                    roleList += result.value
                    roleAdapter?.notifyDataSetChanged()
                }
                is OperationResult.Failure -> Toast.makeText(
                    context,
                    result.error.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun View.showDatePickerDialog(
        selectedDate: Calendar?,
        datePickerListener: DatePickerDialog.OnDateSetListener
    ) {
        val calendar = Calendar.getInstance().apply {
            if (selectedDate != null) {
                time = selectedDate.time
            }
        }
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val dayOfMonth = calendar[Calendar.DAY_OF_MONTH]
        val theme = android.R.style.Theme_Material_Light_Dialog_Alert
        DatePickerDialog(
            context,
            theme,
            datePickerListener,
            year, month, dayOfMonth
        ).show()
    }

    private fun setStartDate(year: Int, month: Int, dayOfMonth: Int) {
        startDate = Calendar.getInstance().apply {
            set(year, month, dayOfMonth)
        }
    }

    private fun setEndDate(year: Int, month: Int, dayOfMonth: Int) {
        endDate = Calendar.getInstance().apply {
            set(year, month, dayOfMonth)
        }
    }


    private fun View.submitEvent() {
        launch {
            val endDate = endDate?.time
            if (endDate == null) {
                Toast.makeText(context, "Не указана дата конца", Toast.LENGTH_SHORT).show()
                return@launch
            }
            val startDate = startDate?.time
            if (startDate == null) {
                Toast.makeText(context, "Не указана дата начала", Toast.LENGTH_SHORT).show()
                return@launch
            }
            val result = withContext(Dispatchers.IO) {
                personRepository.applyForVolunteering(
                    event,
                    EventRegisterForm(
                        comment = event_registration_comment.text.toString(),
                        endDate = endDate,
                        startDate = startDate,
                        event = event,
                        status = 0,
                        role = (event_registration_role_spinner.selectedItem as? Role),
                        user = personRepository.getLoggedUser()!!
                    )
                )
            }
            when (result) {
                is OperationResult.Success -> {
                    findNavController().popBackStack()
                }
                is OperationResult.Failure -> Toast.makeText(context, result.error.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
