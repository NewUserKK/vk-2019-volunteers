package ru.wa285.volunteers.domain.task

import ru.wa285.volunteers.domain.common.OperationResult
import ru.wa285.volunteers.domain.event.model.Event

interface TaskRepository {
    suspend fun getAll(event: Event): OperationResult<List<Task>>
}