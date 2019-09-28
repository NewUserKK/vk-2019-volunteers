package ru.wa285.volunteers.domain.museum

import ru.wa285.volunteers.domain.common.OperationResult
import ru.wa285.volunteers.domain.museum.model.Museum

interface MuseumRepository {
    fun getAll(): OperationResult<List<Museum>>
}