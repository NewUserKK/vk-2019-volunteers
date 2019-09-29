package ru.wa285.volunteers.presentation.common

import org.kodein.di.generic.instance
import ru.wa285.volunteers.presentation.VolunteersApp
import java.text.DateFormat
import java.util.*


fun dateToLocalizedString(calendar: Calendar, locale: Locale): String {
    return calendar.time.toLocalizedString(locale)
}

fun Date.toLocalizedString(locale: Locale = VolunteersApp.locale): String {
    val formatter = DateFormat.getDateInstance(DateFormat.MEDIUM, locale)
    return formatter.format(this)
}