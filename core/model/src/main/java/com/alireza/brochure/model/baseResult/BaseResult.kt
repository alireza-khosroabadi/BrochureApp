package com.alireza.brochure.model.baseResult

import com.alireza.brochure.model.appError.AppError

sealed class BaseResult<out T> {
    data class Success<T>(val data: T, val fromCache: Boolean = false): BaseResult<T>()
    data class Failure(val error: AppError) : BaseResult<Nothing>()
}