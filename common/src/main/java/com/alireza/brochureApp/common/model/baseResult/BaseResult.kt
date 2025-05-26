package com.alireza.brochureApp.common.model.baseResult

import com.alireza.brochureApp.common.model.appError.AppError

sealed class BaseResult<out T> {
    data class Success<T>(val data: T, val fromCache: Boolean = false): BaseResult<T>()
    data class Failure(val error: AppError) : BaseResult<Nothing>()
}