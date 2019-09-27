package ru.newuserkk.volunteers.presentation.common

import java.text.DateFormat
import java.util.*

fun dateToLocalizedString(calendar: Calendar, locale: Locale): String {
    return calendar.time.toLocalizedString(locale)
}

fun Date.toLocalizedString(locale: Locale): String {
    val formatter = DateFormat.getDateInstance(DateFormat.MEDIUM, locale)
    return formatter.format(this)
}