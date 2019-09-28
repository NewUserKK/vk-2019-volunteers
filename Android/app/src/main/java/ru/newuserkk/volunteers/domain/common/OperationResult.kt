package ru.newuserkk.volunteers.domain.common

sealed class OperationResult<T> {
    class Success<T>(val value: T): OperationResult<T>()

    class Failure<T>(val error: Throwable): OperationResult<T>()
}