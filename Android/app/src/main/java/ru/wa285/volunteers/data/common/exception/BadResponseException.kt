package ru.wa285.volunteers.data.common.exception

import retrofit2.Response

class BadResponseException(response: Response<out Any>) :
    Throwable("Bad response: ${response.code()} : ${response.message()}")
