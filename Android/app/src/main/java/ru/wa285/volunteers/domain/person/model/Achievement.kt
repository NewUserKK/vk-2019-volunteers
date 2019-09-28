package ru.wa285.volunteers.domain.person.model

import java.io.Serializable

data class Achievement(
    val name: String,
    val logoUri: String,
    val description: String,
    val id: Long
) : Serializable