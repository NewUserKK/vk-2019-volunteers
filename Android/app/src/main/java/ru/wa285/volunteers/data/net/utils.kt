package ru.wa285.volunteers.data.net

import okhttp3.ResponseBody
import retrofit2.Response
import ru.wa285.volunteers.domain.common.OperationResult

fun <T> Response<T>.toOperationResult(onError: (ResponseBody?) -> Throwable): OperationResult<T> {
    return if (isSuccessful) {
        OperationResult.Success(
            body() ?: return OperationResult.Failure(KotlinNullPointerException("got null"))
        )
    } else {
        OperationResult.Failure(onError(errorBody()))
    }
}

fun <T> T.toOperationResult(): OperationResult<T> {
    return if (this is Throwable) {
        OperationResult.Failure(this)
    } else {
        OperationResult.Success(this)
    }
}