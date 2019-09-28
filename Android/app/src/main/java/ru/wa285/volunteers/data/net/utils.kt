package ru.wa285.volunteers.data.net

import okhttp3.ResponseBody
import retrofit2.Response
import ru.wa285.volunteers.domain.common.OperationResult
import java.net.ConnectException

fun <T> Response<T>.toOperationResult(onError: (Response<T>) -> Throwable): OperationResult<T> {
    return if (isSuccessful) {
        OperationResult.Success(
            body() ?: return OperationResult.Failure(KotlinNullPointerException("got null"))
        )
    } else {
        OperationResult.Failure(onError(this))
    }
}

fun <T> T.toOperationResult(): OperationResult<T> {
    return if (this is Throwable) {
        OperationResult.Failure(this)
    } else {
        OperationResult.Success(this)
    }
}

fun <T> tryConnect(block: () -> OperationResult<T>): OperationResult<T> {
    return try {
        block()
    } catch (e: ConnectException) {
        OperationResult.Failure(e)
    }
}