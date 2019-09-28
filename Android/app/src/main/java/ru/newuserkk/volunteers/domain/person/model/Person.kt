package ru.newuserkk.volunteers.domain.person.model

import java.io.Serializable

data class Person(
    val name: String,
    val avatarUri: String?,
    val id: Long = 0
): Serializable