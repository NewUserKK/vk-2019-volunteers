package ru.newuserkk.volunteers.domain.event.model

import ru.newuserkk.volunteers.domain.museum.model.Museum
import java.util.*

data class Event(
    val name: String,
    val description: String,
    val museum: Museum,
    val avatarUri: String,
    val id: Long = 0,
    val dateStart: Date,
    val dateEnd: Date
)