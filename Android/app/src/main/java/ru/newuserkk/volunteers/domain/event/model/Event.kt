package ru.newuserkk.volunteers.domain.event.model

import ru.newuserkk.volunteers.domain.museum.model.Museum
import java.io.Serializable
import java.util.*

data class Event(
    val name: String,
    val description: String,
    val museum: Museum,
    val avatarUri: String? = null,
    val dateStart: Date,
    val dateEnd: Date,
    val id: Long = 0
    ): Serializable