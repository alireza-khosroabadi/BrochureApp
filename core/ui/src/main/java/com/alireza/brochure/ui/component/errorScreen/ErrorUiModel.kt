package com.alireza.brochure.ui.component.errorScreen

import com.alireza.brochure.model.appError.AppError
import com.alireza.brochure.model.appError.AppError.NoInternet
import com.alireza.brochure.model.appError.AppError.ServerError
import com.alireza.brochure.model.appError.AppError.Timeout
import com.alireza.brochure.model.appError.AppError.Unknown

data class ErrorUiModel(val appError: AppError){
    fun getErrorMessage(): String = when (appError) {
        NoInternet -> "No internet connection. Please check your network."
        is ServerError -> appError.message ?: "Server error. Try again later."
        Timeout -> "Request timed out. Please retry."
        is Unknown -> appError.message ?: "Something went wrong."
    }
}