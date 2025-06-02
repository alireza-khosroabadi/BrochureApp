package com.alireza.brochure.model.appError

sealed class AppError {
    object NoInternet : AppError()
    object Timeout : AppError()
    data class ServerError(val message: String? = null) : AppError()
    data class Unknown(val message: String? = null) : AppError()
}