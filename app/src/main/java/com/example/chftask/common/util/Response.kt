package com.example.chftask.common.util

sealed class Response<T>(
    val data: T?,
    val errorCode: String? = null,
    val errorMessage: String? = null
) {
    class Success<T>(data: T?) : Response<T>(data)
    class Error<T>(errorCode: String, errorMessage: String? = null) :
        Response<T>(errorCode = errorCode, errorMessage = errorMessage,data = null,)
}