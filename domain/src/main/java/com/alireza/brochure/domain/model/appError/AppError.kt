package com.alireza.brochure.domain.model.appError

sealed class AppError {
    object NoInternet : AppError()
    object Timeout : AppError()
    data class ServerError(val message: String? = null) : AppError()
    data class Unknown(val message: String? = null) : AppError()


    fun getErrorMessage(): String = when (this) {
        NoInternet -> "No internet connection. Please check your network."
        is ServerError -> message ?: "Server error. Try again later."
        Timeout -> "Request timed out. Please retry."
        is Unknown -> message ?: "Something went wrong."
    }
}