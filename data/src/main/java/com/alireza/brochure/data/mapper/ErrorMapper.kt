package com.alireza.brochure.data.mapper

import com.alireza.brochure.domain.model.appError.AppError
import kotlinx.coroutines.TimeoutCancellationException
import java.io.IOException

object ErrorMapper {
    fun fromThrowable(e: Throwable): AppError = when (e) {
        is IOException -> AppError.NoInternet
        is TimeoutCancellationException -> AppError.Timeout
        else -> AppError.Unknown(e.message)
    }
}
